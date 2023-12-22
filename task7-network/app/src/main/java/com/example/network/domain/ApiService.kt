package com.example.network.domain

import com.example.network.models.Category
import com.example.network.models.Product
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("categories")
    suspend fun getAllCategories(): List<Category>

    @GET("products")
    suspend fun getAllProducts(): List<Product>

}