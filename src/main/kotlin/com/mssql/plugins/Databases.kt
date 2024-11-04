package com.mssql.plugins

import com.mssql.schema.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.*

fun Application.configureDatabases() {

    val database = Database.connect(
            url = "jdbc:sqlserver://localhost:1433;databaseName=TestAyurware",
            user = "abhishek",
            driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            password = "abhi123"
    )

    /*val userService = UserService(database)
    routing {
        post("/users") {
            val user = call.receive<ExposedUser>()
            val id = userService.create(user)
            call.respond(HttpStatusCode.Created, id)
        }

        get("/") {
            val user = userService.listAll()
            if (user.isEmpty()) {
                call.respond(HttpStatusCode.NoContent)
            }else {
                call.respond(HttpStatusCode.OK, user)
            }
        }

        get("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val user = userService.read(id)
            if (user != null) {
                call.respond(HttpStatusCode.OK, user)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        put("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val user = call.receive<ExposedUser>()
            userService.update(id, user)
            call.respond(HttpStatusCode.OK)
        }

        delete("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            userService.delete(id)
            call.respond(HttpStatusCode.OK)
        }
    }*/
}
