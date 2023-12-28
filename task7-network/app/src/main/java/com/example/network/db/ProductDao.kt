package com.example.network.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.network.models.Category
import com.example.network.models.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg product: Product)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product): Long

    @Query("SELECT * FROM PRODUCT")
    fun getAll(): Flow<List<Product>>
}