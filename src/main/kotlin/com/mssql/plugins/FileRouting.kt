package com.mssql.plugins

import com.mssql.response.CommonResponse
import com.mssql.response.file.FileType
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureFiles() {

    val homeDir = System.getenv("HOME")

    val rootDir = File("$homeDir\\Videos")

    routing {

        staticFiles("file", rootDir)

        route(
            "files"
        ) {
            get {
                val lists = rootDir.listFiles()?.map { FileType(it.name,it.isDirectory) }

                call.respond(
                    CommonResponse(
                        message = "Succsess",
                        data = lists
                    )
                )
            }
        }

    }
}