package routings

import database.service.UserService
import dto.CommonResponse
import dto.user.CreateUserDTO
import dto.user.UserDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureUser() {

    val userService by inject<UserService>()

    routing{
        route("user") {
            get {
                val users = userService.getAllActiveUsers().map { UserDto(
                    username = it.username,
                    email = it.email,
                    age = it.age,
                    isActive = it.isActive,
                    roleId = it.id.value
                ) }
                call.respond(
                    message = CommonResponse(
                        message = "Successful",
                        data = users
                    )
                )
            }
            get("/{user}"){
                val userId = call.parameters["user"]

                if (userId.isNullOrBlank() || userId.toIntOrNull() == null) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = CommonResponse(
                            message = "failed",
                            data = "not enough arguments provided or bad arguments"
                        )
                    )
                } else  {
                    val user = userService.getUserById(userId.toLong())

                    if (user == null) {
                        call.respond(
                            status = HttpStatusCode.NotFound,
                            message = CommonResponse(
                                message = "Successful",
                                data = "user with this id not found"
                            )
                        )
                    } else {
                        call.respond(
                            status = HttpStatusCode.OK,
                            message = CommonResponse(
                                message = "Successful",
                                data = user
                            )
                        )
                    }
                }
            }

            post {
                val userDTO = call.receive<CreateUserDTO>()
                val userId = userService.createUser(
                    username = userDTO.username,
                    password = userDTO.password,
                    email = userDTO.email,
                    isActive = true,
                    age = userDTO.age,
                    roleId = userDTO.role
                )
                call.respond(HttpStatusCode.OK, CommonResponse("User created","UserId: $userId"))
            }

            post("/login") {
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
                            data = null
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
}
