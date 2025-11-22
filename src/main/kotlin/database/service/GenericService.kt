package com.diffy.database.service

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.transactions.transaction

abstract class ExposedGenericDAO<E : LongIdTable, M : Entity<ID>, ID : Comparable<ID>>(
    private val table: E,
    private val entityClass: EntityClass<ID, M>
) : GenericDAO<M, ID> {

    override fun getById(id: ID): M? = transaction {
        entityClass.findById(id)
    }

    override fun getAll(): List<M> = transaction {
        entityClass.all().toList()
    }

    override fun insert(entity: M): M = transaction {
        entity.apply { flush() }
    }

    override fun update(entity: M): M = transaction {
        entity.apply { flush() }
    }

    override fun delete(id: ID): Boolean = transaction {
        entityClass.findById(id)?.delete() != null
    }
}

interface GenericDAO<T, ID : Comparable<ID>> {
    fun getById(id: ID): T?
    fun getAll(): List<T>
    fun insert(entity: T): T
    fun update(entity: T): T
    fun delete(id: ID): Boolean
}