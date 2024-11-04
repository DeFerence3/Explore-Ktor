package com.mssql.response.user

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val username: String,
    val email: String,
    val age: Int,
)