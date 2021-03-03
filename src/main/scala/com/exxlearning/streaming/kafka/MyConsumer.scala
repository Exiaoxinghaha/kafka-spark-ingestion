package com.exxlearning.streaming.kafka

import java.util.Properties

import com.exxlearning.streaming.config.ConfigurationManager

import scala.collection.JavaConversions._
import com.exxlearning.streaming.config.settings.ConsumerSettings
import com.exxlearning.streaming.constants.Constants
import com.exxlearning.streaming.kafka.common.Persistable
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag
import scala.reflect.runtime.universe.{TypeTag, typeOf}

/***
  * 消费者的公共抽象类
  */
@SerialVersionUID(20180701)
abstract class MyConsumer[KDS: TypeTag, VDS: TypeTag](group_id: String, persisters: Array[Persistable])
        extends IngestionExecutor with Serializable { self: ConsumerSettings =>

    // create kafka consumer
    protected def create_consumer[K, V](): KafkaConsumer[K, V] = {

        val properties = new Properties()
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers)
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, group_id)
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, auto_offset_reset)
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enable_auto_commit)
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, typeOf[KDS].typeSymbol.fullName)
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, typeOf[VDS].typeSymbol.fullName)
        new KafkaConsumer[K, V](properties)
    }

    // consume
    def consume[K: ClassTag, V: ClassTag](topic: String, container: Seq[(K, V)]): Unit = {

        // 创建消费者
        val consumer = create_consumer[K, V]()
        try {
            consumer.subscribe(java.util.Arrays.asList(topic))
            while (true) {
                // 读取消息
                val records: ConsumerRecords[K, V] = consumer.poll(polling_timeout.toMillis)

                println(records.count())
                // 把消息封装到容器
                if (records.count() > 0) {
                    val item: Seq[(K, V)] = for (record <- records.iterator().toList) yield  (record.key(), record.value())
                    container.asInstanceOf[ArrayBuffer[(K, V)]].appendAll(item)
                    println(container)
                }
                // 消息持久化到存储系统
                for(persister <- persisters){
                    val resMap = persister.write[K, V](container)
                    try{
                        if(resMap.get("MES_COUNT").toString.trim.toInt > 0 && resMap.get("RDB_NAME").toString.length() > 0){
                            println("**********************************************")
                            println(resMap.get("MES_COUNT") + "message has been insert" + resMap.get("RDB_NAME"))
                            println("**********************************************")
                        }
                    } catch {
                        case ex: Exception => {

                        }

                    }

                }
                // 判断是否自动提交偏移量
                if(!ConfigurationManager.getBooleanProp(Constants.KAFKA_ENABLE_AUTO_COMMIT)){
                    consumer.commitAsync()
                } else {
                    println("###########################")
                    println("#     No message......    #")
                    println("###########################")
                    Thread.sleep(5000)
                    println()
                }
            }
        } finally {
            consumer.close()
        }
    }

    // 执行方法
    override def execute[K: ClassTag, V: ClassTag](topic: String, container: Seq[(K, V)]): Unit = {
        // 初始化sink配置
        if(this.persisters != null && this.persisters.length > 0){
            for(persist <- this.persisters){
                persist.initialize()
            }
        }
        // 消费
        this.consume[K, V](topic, container)
    }
}
