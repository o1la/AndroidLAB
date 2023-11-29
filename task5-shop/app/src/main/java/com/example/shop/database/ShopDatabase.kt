package com.example.shop.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 2)
abstract class ShopDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}

suspend fun populateDatabase(productDao: ProductDao) {
    val predefinedProducts = listOf(
        Product(1, "Teapot", "amazing teapot", 20.0, false, 0),
        Product(2, "Cheese", "delicious", 5.0, true, 1),
        Product(3, "Trumpet", "", 100.0, false, 0),
        Product(4, "Glass", "shiny", 1.0, false, 0),
        Product(5, "Socks", "very warm", 5.0, true, 10),
        Product(6, "Salt", " something ", 2.0, false, 0),
        Product(7, "Pepper", "", 2.0, false, 0),
        Product(8, "Sugar", "sweet", 144.0, false, 0),
        Product(9, "Milk", " ", 5.0, true, 3),
        Product(10, "Egs", "", 12344.0, false, 0),
        )
    predefinedProducts.forEach { productDao.insert(it) }
}