package com.exxlearning.streaming.constants

/***
  * 常量配置对象
  */
object Constants {
    var JDBC_TABLE: String = ""

    var HEAD_NAME: String = ""

    var REGEX: String = ""


    final val DRIVER: String = "driver"
    final val URL: String = "url"
    final val USERNAME: String = "username"
    final val PASSWORD: String = "password"
    final val DEBUG: String = "debug"
    final val HDFSPATH: String = "hdfs_path"

    final val KAFKA_BOOTSTRAP_SERVERS: String = "kafka_bootstrap_servers"
    final val KAFKA_POLLING_TIMEOUT: String = "kafka_polling_timeout"
    final val KAFKA_GROUP_ID: String = "kafka_group_id"
    final val KAFKA_AUTO_OFFSET_RESET: String = "kafka_auto_offset_reset"
    final val KAFKA_ENABLE_AUTO_COMMIT: String = "kafka_enable_auto_commit"
}
