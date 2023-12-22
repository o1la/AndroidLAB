package com.example.network.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("category")
    val category: Int
)
