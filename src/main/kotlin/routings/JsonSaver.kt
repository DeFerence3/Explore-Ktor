package com.diffy.routings

import dto.CommonResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import java.io.File

fun Application.configureJsonSaver(){

    val jsonStorage = environment.config.property("files.jsonpath").getString()

    val jsonStorageDir = File(jsonStorage)
    if (!jsonStorageDir.exists()) {
        jsonStorageDir.mkdirs()
    }

    routing {
        route("api"){
            post("{json}") {
                try {
                    val jsonString = call.receiveText()
                    if (jsonString.isBlank()) {
                        call.respondText("Request body is empty or contains only whitespace.", status = HttpStatusCode.BadRequest)
                        return@post
                    }
                    try {
                        Json.parseToJsonElement(jsonString)
                    } catch (e: Exception) {
                        call.respondText("Invalid JSON format in request body: ${e.message}", status = HttpStatusCode.BadRequest)
                        return@post
                    }
                    val fileName = call.parameters["json"]
                    if (fileName == null) {
                        call.respondText("No json name", status = HttpStatusCode.BadRequest)
                        return@post
                    }
                    val outputFile = File(jsonStorageDir, "$fileName.json")
                    outputFile.writeText(jsonString)
                    call.application.log.info("Saved JSON to: ${outputFile.absolutePath}") // Log success
                    call.respondText("JSON data saved successfully to ${outputFile.name}", status = HttpStatusCode.OK)
                } catch (e: Exception) {
                    call.application.log.error("Error saving JSON request: ${e.message}", e) // Log the error
                    call.respondText("Error processing request: ${e.localizedMessage}", status = HttpStatusCode.InternalServerError)
                }

            }

            get("/{json}") {
                val filenameParam = call.parameters["json"]
                if (filenameParam == null) {
                    call.respondText("Filename not provided.", status = HttpStatusCode.BadRequest)
                    return@get
                }

                val actualFileName = "$filenameParam.json"
                val file = File(jsonStorageDir, actualFileName)

                if (!file.exists() || !file.isFile) {
                    call.application.log.warn("Attempted to access non-existent or invalid file: ${file.absolutePath}")
                    call.respondText("File not found or is not a valid file.", status = HttpStatusCode.NotFound)
                    return@get
                }

                if (!file.canonicalPath.startsWith(jsonStorageDir.canonicalPath)) {
                    call.application.log.warn("Attempted directory traversal detected for filename: $actualFileName")
                    call.respond(HttpStatusCode.Forbidden)
                    return@get
                }

                try {
                    val fileContent = file.readText()
                    val jsonElement = Json.parseToJsonElement(fileContent)
                    call.respond(message = CommonResponse("Data fetched successfully", jsonElement))
                    call.application.log.info("Served file: ${file.name}")
                } catch (e: Exception) {
                    call.application.log.error("Error reading or parsing file ${actualFileName}: ${e.message}", e)
                    call.respondText("Error retrieving or parsing file: ${e.localizedMessage}", status = HttpStatusCode.InternalServerError)
                }
            }
        }
    }
}