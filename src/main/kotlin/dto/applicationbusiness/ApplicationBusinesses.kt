package com.diffy.dto.applicationbusiness

import kotlinx.serialization.Serializable

@Serializable
data class ApplicationBusinesses(
    val applicationBusinessId: Long,
    val isActive: Boolean,
    val title: String,
    val href: String?,
    val availableActions: List<String>,
    val permittedActions: List<String>,
    val childBusinesses: List<ApplicationBusinesses>
)