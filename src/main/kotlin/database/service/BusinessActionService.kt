package com.diffy.database.service

import com.diffy.database.entity.BusinessActionsEntity
import com.diffy.database.table.ApplicationBusinessTable
import com.diffy.database.table.BusinessActionsTable
import com.diffy.database.table.UserRoleBusinessActions
import database.database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class BusinessActionService {
    init {
        transaction(database) {
            SchemaUtils.create(BusinessActionsTable)
        }
    }

    fun getAvailableActions(applicationBusinessId: Long): List<String>{
        return transaction(database) {
            val query = BusinessActionsTable.innerJoin(ApplicationBusinessTable)
                .select(BusinessActionsTable.columns)
                .where(
                    BusinessActionsTable.applicationBusiness.eq(applicationBusinessId)
                )

            BusinessActionsEntity
                .wrapRows(query)
                .map { it.businessAction }
        }
    }

    fun getPermittedActions(
        applicationBusinessId: Long,
        userRoleId: Long
    ): List<String>{
        return transaction(database) {
            val query = ApplicationBusinessTable.innerJoin(BusinessActionsTable).innerJoin(UserRoleBusinessActions)
                .selectAll()
                .where(
                    ApplicationBusinessTable.id.eq(applicationBusinessId)
                            and UserRoleBusinessActions.userRoleID.eq(userRoleId)
                )

            BusinessActionsEntity
                .wrapRows(query)
                .map { it.accessKey }
        }
    }
}