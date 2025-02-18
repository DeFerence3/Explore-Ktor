package dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val username: String,
    val email: String,
    val isActive: Boolean,
    val roleId: Long,
    val age: Int
)