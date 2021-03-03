package com.exxlearning.streaming.kafka.common

import com.exxlearning.streaming.config.ConfigurationManager
import com.exxlearning.streaming.constants.Constants

import scala.reflect.ClassTag


trait Parsable {

    def is_valid(value: String): Boolean = {

        val regex = ConfigurationManager.getStringProp(Constants.REGEX)

        if(value == null || value == ""){
            return false
        }

        if(value.split(regex, -1).length < 1){
            return false
        }

        true
    }

    def parse[T: ClassTag](fields: T): Any
}
