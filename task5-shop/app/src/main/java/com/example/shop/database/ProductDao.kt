package com.example.shop.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM product_table")
    fun getAll(): Flow<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product): Long

    @Query("DELETE FROM product_table WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM product_table")
    fun deleteAll()

    @Query("SELECT * FROM product_table WHERE inCart = 1")
    fun getProductsInCart(): Flow<List<Product>>

    @Query("UPDATE product_table SET inCart = 1 WHERE id = :productId")
    fun addProductToCart(productId: Long)

    @Query("UPDATE product_table SET quantityInCart = quantityInCart + :amount WHERE id = :productId")
    fun increaseProductQuantity(productId: Long, amount: Int)

    @Query("UPDATE product_table SET inCart = 0 AND quantityInCart = 0 WHERE id = :productId")
    fun removeProductFromCart(productId: Long)

    @Query("UPDATE product_table SET quantityInCart = quantityInCart - :amount WHERE id = :productId")
    fun decreaseProductQuantity(productId: Long, amount: Int)

    @Query("SELECT * FROM product_table WHERE id = :productId")
    fun getProduct(productId: Long): Product

}