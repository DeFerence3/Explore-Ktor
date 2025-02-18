package database.service

import com.diffy.database.entity.UserEntity
import com.diffy.database.entity.UserRoleEntity
import com.diffy.database.service.GenericService
import com.diffy.database.table.UserRoleTable
import com.diffy.database.table.UserTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class UserService{

    init {
        transaction {
            SchemaUtils.create(UserTable)
        }
    }

    // Create a new user
    fun createUser(
        username: String,
        password: String,
        email: String,
        isActive: Boolean,
        age: Int,
        roleId: Long,
    ): Long {
        return transaction {
            UserEntity.new {
                this.username = username
                this.password = password
                this.email = email
                this.isActive = isActive
                this.age = age
                this.role = UserRoleEntity[roleId]
            }.id.value
        }
    }

    // Get a user by ID
    fun getUserById(userId: Long): UserEntity? {
        return transaction {
            UserEntity.findById(userId)
        }
    }

    // Update a user
    fun updateUser(userId: Long, username: String? = null, password: String? = null, email: String? = null, isActive: Boolean? = null, age: Int? = null, roleId: Long? = null) {
        transaction {
            val user = UserEntity.findById(userId) ?: throw IllegalArgumentException("User not found")
            username?.let { user.username = it }
            password?.let { user.password = it }
            email?.let { user.email = it }
            isActive?.let { user.isActive = it }
            age?.let { user.age = it }
            roleId?.let { user.role = UserRoleEntity[it] }
        }
    }

    // Delete a user
    fun deleteUser(userId: Long) {
        transaction {
            UserEntity.findById(userId)?.delete()
        }
    }

    // Get all active users
    fun getAllActiveUsers(): List<UserEntity> {
        return transaction {
            UserEntity.find { UserRoleTable.active eq true }.toList()
        }
    }

    // Get users by role
    fun getUsersByRole(roleId: Long): List<UserEntity> {
        return transaction {
            UserEntity.find { UserTable.role eq roleId }.toList()
        }
    }

    fun getAll() = UserEntity.all()
    fun getkjsdnf() = UserEntity.findById(2)
}