package com.diffy.database.entity

import com.diffy.database.table.BusinessActionsTable
import com.diffy.database.table.UserRoleBusinessActions
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BusinessActionsEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<BusinessActionsEntity>(BusinessActionsTable)

    var businessAction by BusinessActionsTable.title
    var accessKey by BusinessActionsTable.accessKey
    var userRolesEntity by UserRoleEntity via UserRoleBusinessActions
}