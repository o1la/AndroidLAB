package com.example.db.dao

import com.example.model.Category
import com.example.model.Product

interface DAOProduct {
    suspend fun allProducts(): List<Product>
    suspend fun product(id: Int): Product?
    suspend fun addNewProduct(name: String, quantity: Int, price: Double, categoryId: Int): Product?
    suspend fun editProduct(id: Int, name: String, quantity: Int, price: Double, categoryId: Int): Boolean
    suspend fun deleteProduct(id: Int): Boolean
}