package com.example.database.models

data class User(
    var id: Long,
    var first: String,
    var second: String = "",
    var last: String,
    var nickname: String,
)
