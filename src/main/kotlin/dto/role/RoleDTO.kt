package dto.role

import kotlinx.serialization.Serializable

@Serializable
data class RoleDTO(
    val id: Long,
    val role: String
)
