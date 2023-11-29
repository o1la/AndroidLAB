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

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()
    fun fetchProductDetails(productId: Long) {
        _product.value = productDao.getProduct(productId)
    }

    fun addToCart(product: Product, quantity: Int) {
        kotlin.runCatching {
            productDao.addProductToCart(product.id)
            productDao.increaseProductQuantity(product.id, quantity)
        }.fold(
            onSuccess = {
                _toastMessage.value = "Successfully added to cart."
            },
            onFailure = {
                _toastMessage.value = "Error occurred."
            }
        )
    }

    fun clearToastMessage() {
        _toastMessage.value = null
    }

    fun removeFromCart(product: Product) {
        kotlin.runCatching {
            productDao.removeProductFromCart(product.id)
        }.fold(
            onSuccess = {
                _toastMessage.value = "Successfully removed from cart."
            },
            onFailure = {
                _toastMessage.value = "Error occurred."
            }
        )
    }

    fun decreaseQuantity(product: Product, quantity: Int) {
        kotlin.runCatching {
            productDao.decreaseProductQuantity(product.id, quantity)
        }.fold(
            onSuccess = {
                _toastMessage.value = "Successfully removed from cart."
            },
            onFailure = {
                _toastMessage.value = "Error occurred."
            }
        )
    }

}
