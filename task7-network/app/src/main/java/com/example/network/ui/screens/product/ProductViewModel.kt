package com.example.network.ui.screens.product

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.db.ProductDao
import com.example.network.domain.ApiService
import com.example.network.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val apiService: ApiService,
    private val productDao: ProductDao
) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        viewModelScope.launch {
            val (products, error) = runCatching {
                apiService.getAllProducts()
            }.fold(
                onSuccess = { it to null },
                onFailure = { e ->
                    null to e.message

                }
            )
            _products.value = products ?: emptyList()
            productDao.insertAll(*products?.toTypedArray() ?: emptyArray())
            _error.value = error
            Log.d("FETCHING-PRODUCTS", error.toString())
        }
    }
}