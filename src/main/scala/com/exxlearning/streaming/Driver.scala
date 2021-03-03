package com.exxlearning.streaming

import com.exxlearning.streaming.config.Settings
import com.exxlearning.streaming.kafka.common.consumer.ArticleConsumer

/***
  * 运行入口
  */
object Driver {

    // main
    def main(args: Array[String]): Unit = {

        // initialize the configuration parameters
        Settings.initialize()

        val consumer = new ArticleConsumer() with Settings.Source.Consumer
        consumer.run()
    }
}
