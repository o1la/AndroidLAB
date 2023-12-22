package com.example.db.dao

import com.example.db.DatabaseFactory.dbQuery
import com.example.model.Categories
import com.example.model.Category
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOCategoryImpl : DAOCategory {

    private fun resultRowToCategory(row: ResultRow) = Category(
        id = row[Categories.id],
        name = row[Categories.name],
        availability = row[Categories.availability],
        colorId = row[Categories.colorId]
    )

    override suspend fun allCategories(): List<Category> = dbQuery {
        Categories.selectAll().map(::resultRowToCategory)
    }

    override suspend fun category(id: Int): Category? = dbQuery {
        Categories
            .select { Categories.id eq id }
            .map(::resultRowToCategory)
            .singleOrNull()
    }

    override suspend fun addNewCategory(id: Int, name: String, availability: Boolean, colorId: Int): Category? = dbQuery {
        val insertStatement = Categories.insert {
            it[Categories.id] = id
            it[Categories.name] = name
            it[Categories.availability] = availability
            it[Categories.colorId] = colorId
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToCategory)
    }

    override suspend fun editCategory(id: Int, name: String, availability: Boolean, colorId: Int): Boolean = dbQuery {
        Categories.update({ Categories.id eq id }) {
            it[Categories.name] = name
            it[Categories.availability] = availability
            it[Categories.colorId] = colorId
        } > 0
    }

    override suspend fun deleteCategory(id: Int): Boolean = dbQuery {
        Categories.deleteWhere { Categories.id eq id } > 0
    }
}

val daoCategory: DAOCategory = DAOCategoryImpl().apply {
    runBlocking {
        val initCategories = listOf(
            Category(1, "Toys", true, 1),
            Category(2, "Clothes", true, 2),
            Category(3, "Shoes", false, 3),
            Category(4, "Hats", true, 4),
            Category(5, "Pet Toys", true, 1),
        )
        if (allCategories().isEmpty()) {
            initCategories.forEach {
                addNewCategory(it.id, it.name, it.availability, it.colorId)
            }
        }
    }
}