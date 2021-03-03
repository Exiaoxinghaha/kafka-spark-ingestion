package com.exxlearning.streaming.kafka.common.parser

import com.exxlearning.streaming.config.ConfigurationManager
import com.exxlearning.streaming.constants.Constants
import com.exxlearning.streaming.kafka.common.Parsable

import scala.reflect.ClassTag

class RDBMSParser extends Parsable {
    
    override def parse[T: ClassTag](fields: T): String = {

//        val head: String = ConfigurationManager.getStringProp(Constants.HEAD_NAME)
//        val headArray: Array[String] = head.split(ConfigurationManager.getStringProp(Constants.REGEX), -1)
        // 获取需要插入的表名
        val table = ConfigurationManager.getStringProp(Constants.JDBC_TABLE)
        val sql = new StringBuffer()
        sql.append("insert into " + table + " (")
//        for(i <- 0 to headArray.length) {
//            if(i == headArray.length - 1){
//                sql.append(headArray(i) + ") ")
//            }else {
//                sql.append(headArray(i) + ",")
//            }
//        }
//        sql.append("values (")
//        for(i <- 0 to headArray.length) {
//            if(i == headArray.length - 1){
//                sql.append(headArray(i) + ") ")
//            }else {
//                sql.append(headArray(i) + ",")
//            }
//        }
        sql.toString
    }
}
