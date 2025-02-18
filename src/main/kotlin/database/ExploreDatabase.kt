package database

import org.jetbrains.exposed.sql.*

val database = Database.connect(
    url = "jdbc:sqlserver://localhost:1434;databaseName=Authentication;encrypt=false",
    user = "abhishek",
    driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver",
    password = "abhi123"
)