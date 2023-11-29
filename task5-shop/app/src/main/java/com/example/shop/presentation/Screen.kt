package com.example.shop.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.shop.R

const val PRODUCT_ID_KEY = "id"
sealed class Screen(val route: String, val icon: ImageVector, @StringRes val title: Int) {

    object Products : Screen("products", Icons.AutoMirrored.Filled.List, R.string.products)
    object Cart : Screen("cart", Icons.Filled.ShoppingCart, R.string.cart)
    object ProductsDetails : Screen("product/{$PRODUCT_ID_KEY}", Icons.Default.Info, R.string.product_details)
}
