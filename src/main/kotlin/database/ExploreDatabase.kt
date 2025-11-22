package database

import org.jetbrains.exposed.sql.Database

val database = Database.connect(
    url = "jdbc:sqlserver://localhost:1433;databaseName=AuthDB;encrypt=false;trustServerCertificate=true",
    user = "abhishek",
    driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver",
    password = "abhi123"
)