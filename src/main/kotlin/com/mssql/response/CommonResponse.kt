package com.mssql.response

import kotlinx.serialization.Serializable

@Serializable
data class CommonResponse<T>(
    val message: String,
    val data: T
)