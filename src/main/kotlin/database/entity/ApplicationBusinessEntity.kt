package com.diffy.database.entity

import com.diffy.database.table.ApplicationBusinessTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ApplicationBusinessEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ApplicationBusinessEntity>(ApplicationBusinessTable)

    var applicationBusinessId by ApplicationBusinessTable.id
    var isActive by ApplicationBusinessTable.isActive
    var title by ApplicationBusinessTable.title
    var href by ApplicationBusinessTable.href
    var parentBusiness by ApplicationBusinessTable.parentBusiness
}