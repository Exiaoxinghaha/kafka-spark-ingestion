package com.exxlearning.streaming.config.settings

import com.exxlearning.streaming.config.ConfigurationManager
import com.exxlearning.streaming.constants.Constants

import scala.concurrent.duration.Duration

trait ConsumerSettings extends KafkaSettings {

    // polling timeout
    def polling_timeout: Duration =
        Duration(ConfigurationManager.getStringProp(Constants.KAFKA_POLLING_TIMEOUT))

    def auto_offset_reset: String =
        ConfigurationManager.getStringProp(Constants.KAFKA_AUTO_OFFSET_RESET)

    def enable_auto_commit: String =
        ConfigurationManager.getStringProp(Constants.KAFKA_ENABLE_AUTO_COMMIT)


}
