package com.example.db.dao

import com.example.db.DatabaseFactory.dbQuery
import com.example.model.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOProductImpl : DAOProduct {

    private fun resultRowToProduct(row: ResultRow) = Product(
        id = row[Products.id],
        name = row[Products.name],
        quantity = row[Products.quantity],
        price = row[Products.price],
        category = row[Products.categoryId]
    )
    override suspend fun allProducts(): List<Product> = dbQuery {
        Products.selectAll().map(::resultRowToProduct)
    }

    override suspend fun product(id: Int): Product? = dbQuery {
        Products
            .select { Products.id eq id }
            .map(::resultRowToProduct)
            .singleOrNull()
    }

    override suspend fun addNewProduct(id: Int, name: String, quantity: Int, price: Double, categoryId: Int): Product? = dbQuery {
        val insertStatement = Products.insert {
            it[Products.id] = id
            it[Products.name] = name
            it[Products.quantity] = quantity
            it[Products.price] = price
            it[Products.categoryId] = categoryId
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToProduct)
    }

    override suspend fun editProduct(id: Int, name: String, quantity: Int, price: Double, categoryId: Int): Boolean = dbQuery {
        Products.update({ Products.id eq id }) {
            it[Products.name] = name
            it[Products.quantity] = quantity
            it[Products.price] = price
            it[Products.categoryId] = categoryId
        } > 0
    }

    override suspend fun deleteProduct(id: Int): Boolean = dbQuery {
        Products.deleteWhere { Products.id eq id } > 0
    }

}

val dao: DAOProduct = DAOProductImpl().apply {
    runBlocking {
        val initProducts = listOf(
            Product(0, "Bear", 50, 19.99, 1),
            Product(1, "T-shirt", 5, 29.99, 2),
            Product(2, "Boots", 0, 59.99, 3),
            Product(3, "Cap", 1, 9.99, 4),
            Product(4, "Lego", 1, 100.00, 1),
        )
        if (allProducts().isEmpty()) {
            initProducts.forEach {
                addNewProduct(it.id, it.name, it.quantity, it.price, it.category)
            }
        }
    }
}