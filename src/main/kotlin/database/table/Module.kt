package com.diffy.database.entity

import org.jetbrains.exposed.dao.id.LongIdTable

object Module : LongIdTable("Modules") {
    val moduleName = varchar("ModuleName", 100)
    val moduleKey = varchar("ModuleKey", 50).uniqueIndex()
    val indexUrl = varchar("IndexUrl", 300)
    val description = varchar("Description", 500).nullable()
    val styleClass = varchar("StyleClass", 100).nullable()
    val icon = varchar("Icon", 100).nullable()
    val active = bool("IsActive").default(true)
    val apiModule = bool("IsApiModule").default(false)
}
