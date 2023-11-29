package com.example.shop.presentation.products.details

import androidx.lifecycle.ViewModel
import com.example.shop.database.Product
import com.example.shop.database.ProductDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductDetailsViewModel(private val productDao: ProductDao) : ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product.asStateFlow()
    fun fetchProductDetails(productId: Long) {
        _product.value = productDao.getProduct(productId)
    }

    fun addToCart(product: Product, quantity: Int) {
        productDao.addProductToCart(product.id)
        productDao.increaseProductQuantity(product.id, quantity)
    }

}
