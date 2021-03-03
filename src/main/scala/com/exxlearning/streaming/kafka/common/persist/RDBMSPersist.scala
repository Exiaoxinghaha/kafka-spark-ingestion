package com.exxlearning.streaming.kafka.common.persist

import com.exxlearning.streaming.config.Settings
import com.exxlearning.streaming.jdbc.JDBCHelper
import com.exxlearning.streaming.kafka.common.{Parsable, Persistable}

import scala.reflect.ClassTag

/***
  * 结构型存储系统
  * @param parser
  *               转换器，返回sql语句
  */
class RDBMSPersist(parser: Parsable) extends Persistable{

    override def initialize(): Unit = {/* do nothing*/}

    // 把消息写入数据库
    override def write[K: ClassTag, V: ClassTag](container: Seq[(K, V)]): Map[String, String] = {
        val it = container.iterator
        while(it.hasNext){
            val sql = this.parser.parse[String](it.next()._2.toString)
            println(sql)
//            (new JDBCHelper() with Settings.Database.Settings).process(sql)
        }

        Map("MES_COUNT" -> container.length.toString, "RDB_NAME" -> "test")
    }
}
