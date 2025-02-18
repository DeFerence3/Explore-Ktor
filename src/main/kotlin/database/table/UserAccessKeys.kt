package com.diffy.database.entity

import com.diffy.database.table.UserTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp

object UserAccessKeys : LongIdTable("UserAccessKey","AccessKeyID") {
    val key = varchar("AccessKey", 200).nullable()
    val userId = reference(name = "UserID", foreign =  UserTable, onDelete = null)
    val addedDate = timestamp("AddedDate").nullable()
}
