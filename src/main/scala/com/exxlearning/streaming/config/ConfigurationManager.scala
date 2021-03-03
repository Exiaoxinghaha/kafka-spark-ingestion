package com.exxlearning.streaming.config

import java.util.Properties

/***
  * 配置管理组件
  */
object ConfigurationManager {

    // create properties object
    private val properties: Properties = new Properties()

    // get inputstream
    private val in = ConfigurationManager
            .getClass
            .getClassLoader
            .getResourceAsStream("settings.properties")

    // load
    properties.load(in)


    // get string variable
    def getStringProp(key: String): String = properties.getProperty(key)

    // get bool variable
    def getBooleanProp(key: String): Boolean = properties.getProperty(key).toBoolean

    // get int variable
    def getIntProp(key: String): Int = properties.getProperty(key).toInt

}
