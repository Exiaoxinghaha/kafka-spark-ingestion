package com.exxlearning.streaming.kafka

import scala.reflect.ClassTag

/***
  * 执行任务的接口
  */
trait IngestionExecutor {

    def execute[K: ClassTag, V: ClassTag](topic: String, container: Seq[(K, V)]): Unit
}
