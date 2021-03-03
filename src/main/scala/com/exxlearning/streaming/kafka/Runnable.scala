package com.exxlearning.streaming.kafka

import scala.reflect.ClassTag

trait Runnable {

    def run[K: ClassTag, V: ClassTag](topic: String, container: Seq[(K, V)] => Unit): Unit
}
