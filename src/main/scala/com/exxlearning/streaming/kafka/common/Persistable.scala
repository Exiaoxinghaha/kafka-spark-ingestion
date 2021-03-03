package com.exxlearning.streaming.kafka.common

import scala.reflect.ClassTag

trait Persistable {

    def initialize(): Unit

    def write[K: ClassTag, V: ClassTag](container: Seq[(K, V)]): Map[String, String]
}
