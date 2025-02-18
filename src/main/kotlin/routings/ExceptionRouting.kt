package routings

import dto.CommonResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.SerializationException


fun Application.configureExceptionRouting() {
    install(StatusPages) {

        status(HttpStatusCode.NotFound){ call, status ->
            call.respond(
                status = status,
                message = CommonResponse(
                    message = "Not Found",
                    data = null
                )
            )
        }

        exception<SerializationException>{ call, cause ->
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = CommonResponse(
                    message = "Wrong request body",
                    data = cause.toString()
                )
            )
        }

        exception<BadRequestException>{ call, cause ->
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = CommonResponse(
                    message = "Wrong request body or arguments",
                    data = cause.toString()
                )
            )
        }

        exception<Throwable>{ call, cause ->
            cause.printStackTrace()
            call.respond(
                status = HttpStatusCode.InternalServerError,
                message = CommonResponse(
                    message = "An unexpected error occurred",
                    data = cause.toString()
                )
            )
        }
    }
}
