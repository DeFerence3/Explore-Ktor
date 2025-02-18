package com.diffy.database.entity

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.date

object FinancialYear : LongIdTable("FinancialYear") {
    val title = varchar("Title", 100)
    val year = integer("Year")
    val active = bool("IsActive").default(true)
    val current = bool("IsCurrent").default(true)
    val persistenceName = varchar("PersistenceName", 56)
    val startDate = date("StartDate")
    val endDate = date("EndDate")
    val module = reference("ModuleID", Module, onDelete = ReferenceOption.CASCADE)
}
