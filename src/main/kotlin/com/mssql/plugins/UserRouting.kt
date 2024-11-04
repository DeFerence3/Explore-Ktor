package com.mssql.plugins

import com.mssql.response.CommonResponse
import com.mssql.response.user.UserDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureUser() {

    routing{

        get{
            call.respond(
                CommonResponse(
                    message = "Successful",
                    data = "Data"
                )
            )
        }

        get("user"){
            call.respond(
                CommonResponse(
                    message = "Successful",
                    data = UserDto(
                        "Explore-Ktor",
                        "explore@ktor.com",
                        2
                    )
                )
            )
        }

        post("login") {
            val username = call.request.queryParameters["username"]
            val password = call.request.queryParameters["password"]

            if (username == null || password == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = CommonResponse(
                        message = "Failed",
                        data = "either username or password is empty"
                    )
                )
            } else if (username == "admin" && password == "pass"){
                call.respond(
                    CommonResponse(
                        message = "Successful",
                        data = ""
                    )
                )
            } else {
                call.respond(
                    status = HttpStatusCode.Unauthorized,
                    message = CommonResponse(
                        message = "Failed",
                        data = "Username or password not matching"
                    )
                )
            }
        }
    }
}
