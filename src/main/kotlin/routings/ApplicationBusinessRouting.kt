package com.diffy.routings

import com.diffy.database.service.ApplicationBusinessService
import dto.CommonResponse
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureApplicationBusinessRoute() {

    val applicationBusinessService by inject<ApplicationBusinessService>()

    routing {
        route("application"){
            get {
                val applicationBusinesses = applicationBusinessService.getParentBusinessByModule()
                call.respond(
                    message = CommonResponse(
                        message = "Successful",
                        data = applicationBusinesses
                    )
                )
            }
        }
    }
}