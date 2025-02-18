package database.service

import database.database
import com.diffy.database.table.UserRoleTable
import com.diffy.dto.role.CreateRoleDTO
import dto.role.RoleDTO
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class RoleService {

    init {
        transaction(database) {
            SchemaUtils.create(UserRoleTable)
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun create(user: CreateRoleDTO): Long = dbQuery {
        UserRoleTable.insert {
            it[role] = user.role
        }[UserRoleTable.id].value
    }

    suspend fun read(id: Long): RoleDTO? {
        return dbQuery {
            UserRoleTable.selectAll().where(UserRoleTable.id.eq(id))
                .map { RoleDTO(
                    id = it[UserRoleTable.id].value,
                    role = it[UserRoleTable.role]
                ) }
                .singleOrNull()
        }
    }

    suspend fun update(id: Long, roleDto: CreateRoleDTO) {
        dbQuery {
            UserRoleTable.update({ UserRoleTable.id eq id }) {
                it[role] = roleDto.role
            }
        }
    }

    suspend fun listAll(): List<RoleDTO> {
        return dbQuery {
            UserRoleTable.selectAll()
                .map { RoleDTO(
                    id = it[UserRoleTable.id].value,
                    role = it[UserRoleTable.role]
                ) }
        }
    }

    suspend fun delete(roleId: Long) {
        dbQuery {
            UserRoleTable.deleteWhere { id.eq(roleId) }
        }
    }
}