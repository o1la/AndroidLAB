package com.example.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Category(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("availability") val availability: Boolean,
    @SerialName("colorId") val colorId: Int
)

object Categories : Table() {
    val id = integer("id")
    val name = varchar("name", 50)
    val availability = bool("availability")
    val colorId = integer("colorId")
    override val primaryKey = PrimaryKey(id, name = "PK_Category_Id")
}
