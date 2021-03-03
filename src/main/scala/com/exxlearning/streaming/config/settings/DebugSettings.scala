package com.exxlearning.streaming.config.settings

import org.apache.spark.sql.DataFrame

trait DebugSettings {

    // debug flag
    def debug: Boolean

    // hdfs dir
    def hdfs_path: String

    // write
    def write(df: DataFrame, dir: String): Unit = {
        if(debug){
            df.write.format("csv")
                    .option("header", "true")
                    .mode("overwrite")
                    .save("%s/%s".format(hdfs_path, dir))
        }
    }
}
