package com.diffy.database.table

import org.jetbrains.exposed.dao.id.LongIdTable

object UserTable : LongIdTable("User","UserId") {
    val username = varchar("Username", 50)
    val password = varchar("Password", 50)
    val email = varchar("Email", 50)
    val isActive = bool("IsActive")
    val age = integer("Age")
    val role = reference("RoleId", UserRoleTable)
}