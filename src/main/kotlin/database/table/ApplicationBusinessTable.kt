package com.diffy.database.table

import com.diffy.database.entity.Module
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object ApplicationBusinessTable : LongIdTable("ApplicationBusiness","ApplicationBusinessID") {
    val menuLabel = varchar("MenuLabel", 100).nullable()
    val title = varchar("Title", 100)
    val href = varchar("Href", 300).nullable()
    val shortHref = varchar("ShortHref", 300).nullable()
    val shortCut = varchar("ShortCut", 10).nullable()
    val icon = varchar("Icon", 300).nullable()
    val sortOrder = integer("SortOrder")
    val isActive = bool("IsActive").default(true)
    val moduleId = reference("ModuleID", Module, onDelete = ReferenceOption.CASCADE)
    val parentBusiness = reference("ParentBusinessID", ApplicationBusinessTable).nullable()
}
