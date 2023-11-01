package com.example.model

import kotlinx.serialization.SerialName
import org.jetbrains.exposed.dao.id.IntIdTable

data class Category(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("availability") val availability: Boolean,
    @SerialName("colorId") val colorId: Int
)

object Categories : IntIdTable() {
    val name = varchar("name", 50)
    val availability = bool("availability")
    val colorId = integer("colorId")
}
