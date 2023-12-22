package com.example.network.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Category(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("availability")
    val availability: Boolean,
    @SerializedName("colorId")
    val colorId: Int
)
