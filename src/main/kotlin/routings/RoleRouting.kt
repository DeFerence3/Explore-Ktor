package routings

import database.service.RoleService
import dto.CommonResponse
import com.diffy.dto.role.CreateRoleDTO
import dto.role.RoleDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRoleRouting(){

    val roleService by inject<RoleService>()

    routing{
        route("/role") {
            get {
                val roles = roleService.listAll().map { RoleDTO(
                    id = it.id,
                    role = it.role
                ) }

                val message = if (roles.isEmpty()) {
                    CommonResponse(
                        message = "Successful, No Content",
                        data = roles
                    )
                } else {
                    CommonResponse(
                        message = "Successful",
                        data = roles
                    )
                }

                call.respond(
                    status = HttpStatusCode.OK,
                    message = message
                )
            }
            get("/{roleId}"){
                val roleId = call.parameters["roleId"]

                val (status,message) = if (roleId.isNullOrBlank() || roleId.toIntOrNull() == null) {
                    Pair(
                        HttpStatusCode.BadRequest,
                        CommonResponse(
                            message = "failed",
                            data = "not enough arguments provided or bad arguments"
                        )
                    )

                } else  {
                    val role = roleService.read(roleId.toLong())

                    Pair(HttpStatusCode.BadRequest,
                        CommonResponse(
                            message = "failed",
                            data = "not enough arguments provided or bad arguments"
                        )
                    )

                    if (role == null) {
                        Pair(HttpStatusCode.NotFound,
                             CommonResponse(
                                message = "Successful",
                                data = "role with this id not found"
                            )
                        )

                    } else {
                        Pair(HttpStatusCode.OK,
                            CommonResponse(
                                message = "Successful",
                                data = RoleDTO(
                                    id = role.id,
                                    role = role.role
                                )
                            )
                        )
                    }
                }

                call.respond(
                    status = status,
                    message = message
                )
            }

            post {

                val roleDTO = call.receive<CreateRoleDTO>()
                val roleId = roleService.create(roleDTO)
                val (status,message)  = Pair(HttpStatusCode.OK, CommonResponse("Role created","RoleId: $roleId"))

                call.respond(status = status,message = message)
            }

        }
    }
}