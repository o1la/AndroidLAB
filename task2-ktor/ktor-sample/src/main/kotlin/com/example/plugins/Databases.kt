package com.example.plugins

import com.example.model.Categories
import com.example.model.Products
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    Database.connect("jdbc:sqlite:sample.db", driver = "org.sqlite.JDBC")
    transaction {
        SchemaUtils.create(Products)
        SchemaUtils.create(Categories)
    }
}
