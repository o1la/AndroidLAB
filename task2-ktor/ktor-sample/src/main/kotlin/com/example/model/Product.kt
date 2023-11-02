package com.example.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Product(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("quantity") val quantity: Int,
    @SerialName("price") val price: Double,
    @SerialName("category") val category: Int
)

object Products : Table() {
    val id = integer("id")
    val name = varchar("name", 50)
    val quantity = integer("quantity")
    val price = double("price")
    val categoryId = integer("category_id").references(Categories.id)
    override val primaryKey = PrimaryKey(id, name = "PK_Products_Id")
}

