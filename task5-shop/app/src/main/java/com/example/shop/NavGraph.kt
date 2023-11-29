package com.example.shop

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shop.presentation.PRODUCT_ID_KEY
import com.example.shop.presentation.Screen
import com.example.shop.presentation.cart.Cart
import com.example.shop.presentation.cart.CartViewModel
import com.example.shop.presentation.products.details.ProductDetails
import com.example.shop.presentation.products.Products
import com.example.shop.presentation.products.ProductsViewModel
import com.example.shop.presentation.products.details.ProductDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = Screen.Products.route, Modifier.padding(paddingValues)) {
        composable(Screen.Products.route) {
            Products(navController)
        }
        composable(Screen.Cart.route) {
            Cart(navController)
        }
        composable(Screen.ProductsDetails.route) {
            val id = it.arguments?.getString(PRODUCT_ID_KEY)?.toLongOrNull()
            if (id != null) {
                ProductDetails(id)
            }
        }
    }
}