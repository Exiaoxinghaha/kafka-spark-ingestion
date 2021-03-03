package com.exxlearning.streaming.config.settings

@SerialVersionUID(20190701)
trait KafkaSettings extends Serializable{

    // broker url list
    def bootstrap_servers: String
}
