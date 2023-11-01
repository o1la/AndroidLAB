package com.example.model

import kotlinx.serialization.SerialName
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table

data class Category(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("availability") val availability: Boolean,
    @SerialName("colorId") val colorId: Int
)

object Categories : Table() {
    val id = Products.integer("id")
    val name = varchar("name", 50)
    val availability = bool("availability")
    val colorId = integer("colorId")
}
