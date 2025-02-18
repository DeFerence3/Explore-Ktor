package com.diffy.database.table

import com.diffy.database.entity.Module
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime

object UserRoleTable: LongIdTable("Roles","RoleId") {
    val role = UserRoleTable.varchar("RoleName", 100)
    val accessType = UserRoleTable.varchar("AccessType", 50)
    val yearAccess = UserRoleTable.varchar("YearAccess", 50)
    val active = UserRoleTable.bool("IsActive").default(true)
    val builtIn = UserRoleTable.bool("IsBuiltIn").default(false)
    val addedDate = datetime("AddedDate")
    val modules = reference("ModuleID", Module, onDelete = ReferenceOption.CASCADE).nullable()
}