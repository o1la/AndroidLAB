package com.example.database.models

data class Cart(
    var id: Long,
    var products: List<Long> = listOf(),
    var total: Double = 0.0,
    var userId: Long = 0
)
