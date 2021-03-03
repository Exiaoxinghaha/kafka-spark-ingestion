package com.exxlearning.streaming.common

import scala.reflect.ClassTag

trait SQLStatement {

    def make[T: ClassTag](value: T): String
}
