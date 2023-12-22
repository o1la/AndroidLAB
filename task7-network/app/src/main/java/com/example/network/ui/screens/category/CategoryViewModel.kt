package com.example.network.ui.screens.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.domain.ApiService
import com.example.network.models.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val apiService: ApiService
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        viewModelScope.launch {
            val (categories, error) = runCatching {
                apiService.getAllCategories()
            }.fold(
                onSuccess = { it to null },
                onFailure = { e -> null to e.message }
            )
            _categories.value = categories ?: emptyList()
            _error.value = error
            Log.d("FETCHING-CATEGORY", error.toString())
        }
    }
}