package com.exxlearning.streaming.jdbc

import java.sql.{Connection, DriverManager, Statement}

import com.exxlearning.streaming.config.settings.DatabaseSettings


@SerialVersionUID(20180703)
class JDBCHelper extends Serializable { self: DatabaseSettings =>

    var connection: Connection = _
    var statement: Statement = _

    def getInstance: JDBCHelper = ???

    def getConnection: Connection = ???

    def returnConnection: Boolean = ???

    def process(value: Any): Boolean = ???

}
