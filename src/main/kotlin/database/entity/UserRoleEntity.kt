package com.diffy.database.entity

import com.diffy.database.table.UserRoleBusinessActions
import com.diffy.database.table.UserRoleTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserRoleEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserRoleEntity>(UserRoleTable)

    var roleName by UserRoleTable.role
    var businessActionsEntity by BusinessActionsEntity via UserRoleBusinessActions
}