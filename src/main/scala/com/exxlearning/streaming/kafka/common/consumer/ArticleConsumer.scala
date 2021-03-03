package com.exxlearning.streaming.kafka.common.consumer

import com.exxlearning.streaming.config.settings.ConsumerSettings
import com.exxlearning.streaming.kafka.MyConsumer
import com.exxlearning.streaming.kafka.common.parser.RDBMSParser
import com.exxlearning.streaming.kafka.common.persist.RDBMSPersist
import org.apache.avro.SchemaBuilder.ArrayBuilder
import org.apache.kafka.common.serialization.StringDeserializer

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

@SerialVersionUID(20190706)
class ArticleConsumer extends MyConsumer[StringDeserializer, StringDeserializer]("test", Array(new RDBMSPersist(new RDBMSParser)))
       with Runnable with Serializable { self: ConsumerSettings =>

    override def run(): Unit = {
        val list = ArrayBuffer[(StringDeserializer, StringDeserializer)]()
        this.execute[StringDeserializer, StringDeserializer]("user", list)
    }

}
