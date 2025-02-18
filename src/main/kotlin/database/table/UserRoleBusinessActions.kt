package com.diffy.database.table

import org.jetbrains.exposed.sql.Table

object UserRoleBusinessActions : Table("UserRoleBusinessActions") {
    val userRoleID = reference("UserRoleID", UserRoleTable.id)
    val businessActionID = reference("BusinessActionID", BusinessActionsTable.id)
    override val primaryKey = PrimaryKey(userRoleID, businessActionID)
}