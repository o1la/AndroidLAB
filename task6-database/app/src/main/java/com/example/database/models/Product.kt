package com.example.database.models

data class Product(
    var id: Long,
    var name: String,
    var price: Double,
    var description: String,
    var category: String
)
