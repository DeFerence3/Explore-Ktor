package com.diffy.database.table

import org.jetbrains.exposed.dao.id.LongIdTable

object BusinessActionsTable : LongIdTable("BusinessActions","BusinessActionID") {

    val accessKey = varchar("AccessKey", 50)
    val title = varchar("Title", 100)
    val href = varchar("Href", 300).nullable()
    val icon = varchar("Icon", 300).nullable()
    val shortCut = varchar("ShortCut", 20).nullable()
    val active = bool("IsActive").default(true)
    val applicationBusiness = reference(
        name = "ApplicationBusinessID",
        foreign = ApplicationBusinessTable
    )
}