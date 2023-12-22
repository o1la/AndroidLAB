package com.example.network

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.network.ui.screens.category.CategoriesList
import com.example.network.ui.screens.product.ProductsList

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "categories") {
        composable("categories") {
            CategoriesList(navController)
        }
        composable("products/{id}") {
            val id = it.arguments?.getString("id")?.toIntOrNull()
            if (id != null) {
                ProductsList(id)
            }
        }
    }
}