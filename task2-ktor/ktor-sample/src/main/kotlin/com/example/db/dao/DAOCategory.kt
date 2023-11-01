package com.example.db.dao

import com.example.model.Category

interface DAOCategory {

    suspend fun allCategories(): List<Category>
    suspend fun category(id: Int): Category?
    suspend fun addNewCategory(name: String, availability: Boolean, colorId: Int): Category?
    suspend fun editCategory(id: Int, name: String, availability: Boolean, colorId: Int): Boolean
    suspend fun deleteCategory(id: Int): Boolean

}
