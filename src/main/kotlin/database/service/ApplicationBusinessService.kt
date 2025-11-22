package com.diffy.database.service

import com.diffy.database.entity.ApplicationBusinessEntity
import com.diffy.database.table.ApplicationBusinessTable
import com.diffy.dto.applicationbusiness.ApplicationBusinesses
import database.database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNull
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class ApplicationBusinessService {

    private val businessActionService = BusinessActionService()

    init {
        transaction(database) {
            SchemaUtils.create(ApplicationBusinessTable)
        }
    }

    fun getParentBusinessByModule(): List<ApplicationBusinesses> {
        return transaction(database) {
            val query = ApplicationBusinessTable
                .select(ApplicationBusinessTable.columns)
                .where(
                    ApplicationBusinessTable.moduleId.eq(2) and
                            ApplicationBusinessTable.parentBusiness.isNull()
                )

            ApplicationBusinessEntity
                .wrapRows(query)
                .map {
                    ApplicationBusinesses(
                        applicationBusinessId = it.applicationBusinessId.value,
                        isActive = it.isActive,
                        title = it.title,
                        href = it.href,
                        availableActions = businessActionService.getAvailableActions(it.applicationBusinessId.value),
                        permittedActions = businessActionService.getPermittedActions(it.applicationBusinessId.value,1),
                        childBusinesses = getChildBusiness(it.id.value)
                    )
                }
        }
    }

    private fun getChildBusiness(parentBusinessId: Long): List<ApplicationBusinesses> {
        return transaction(database) {
            val query = ApplicationBusinessTable
                .select(ApplicationBusinessTable.columns)
                .where(ApplicationBusinessTable.parentBusiness.eq(parentBusinessId))

            ApplicationBusinessEntity
                .wrapRows(query)
                .map { entity: ApplicationBusinessEntity ->
                    ApplicationBusinesses(
                        applicationBusinessId = entity.applicationBusinessId.value,
                        isActive = entity.isActive,
                        title = entity.title,
                        href = entity.href,
                        availableActions = businessActionService.getAvailableActions(entity.applicationBusinessId.value),
                        permittedActions = businessActionService.getPermittedActions(entity.applicationBusinessId.value,1),
                        childBusinesses = getChildBusiness(entity.id.value)
                    )
                }
        }
    }
}