package dto.user

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserDTO(
    val username: String,
    val password: String,
    val email: String,
    val role: Long,
    val age: Int
)