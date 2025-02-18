package com.diffy.database.entity

import com.diffy.database.table.UserTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserEntity>(UserTable)

    var username by UserTable.username
    var password by UserTable.password
    var email by UserTable.email
    var isActive by UserTable.isActive
    var age by UserTable.age
    var role by UserRoleEntity referencedOn UserTable.role // Assuming UserRoleEntity is defined elsewhere
}