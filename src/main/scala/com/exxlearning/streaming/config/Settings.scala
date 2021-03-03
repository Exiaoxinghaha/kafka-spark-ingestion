package com.exxlearning.streaming.config

import com.exxlearning.streaming.config.settings.{ConsumerSettings, DatabaseSettings, DebugSettings}
import com.exxlearning.streaming.constants.Constants

import scala.concurrent.duration.Duration

object Settings {

    // debug flag
    private var __debug: Boolean = _
    // hdfs path
    private var __hdfs_path: String  = _

    /***
      * message-oriented middleware configuration
      */
    object Source {

        private var __bootstrap_servers: String = _

        /***
          * kafka consumer params
          */
        trait Consumer extends ConsumerSettings with DebugSettings {

            override def bootstrap_servers: String = __bootstrap_servers

            override def debug: Boolean = __debug

            override def hdfs_path: String = __hdfs_path

        }

        def initialize(): Unit ={
            this.__bootstrap_servers =
                    ConfigurationManager.getStringProp(Constants.KAFKA_BOOTSTRAP_SERVERS)
            println(this.__bootstrap_servers)
        }

    }

    /***
      * RDBMS configuration
      */
    object Database {

        private var __driver: String = _

        private var __url: String = _

        private var __username: String = _

        private var __password: String = _

        trait Settings extends DatabaseSettings with DebugSettings {

            override def driver: String = __driver

            override def url: String = __url

            override def username: String = __username

            override def password: String = __password

            override def debug: Boolean = __debug

            override def hdfs_path: String = __hdfs_path

        }

        def initialize(): Unit = {
            this.__url = ConfigurationManager.getStringProp(Constants.URL)
            this.__driver = ConfigurationManager.getStringProp(Constants.DRIVER)
            this.__username = ConfigurationManager.getStringProp(Constants.USERNAME)
            this.__password = ConfigurationManager.getStringProp(Constants.PASSWORD)
            println(__url, __driver, __username, __password)
        }

    }

    def initialize(): Unit = {
        this.Database.initialize()
        this.Source.initialize()
        this.__debug = ConfigurationManager.getBooleanProp(Constants.DEBUG)
        this.__hdfs_path = ConfigurationManager.getStringProp(Constants.HDFSPATH)
        println(__debug, __hdfs_path)
    }
}
