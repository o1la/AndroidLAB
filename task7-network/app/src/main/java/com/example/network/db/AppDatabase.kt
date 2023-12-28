package com.example.network.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.network.models.Category
import com.example.network.models.Product

@Database(entities = [Category::class, Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
}
