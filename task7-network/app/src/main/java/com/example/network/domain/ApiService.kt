package com.example.network.domain

import com.example.network.models.Category
import com.example.network.models.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("categories")
    suspend fun getAllCategories(): List<Category>

    @GET("products")
    suspend fun getAllProducts(): List<Product>

    @POST("products")
    suspend fun addProducts(@Body product: Product): Response<Unit>

}