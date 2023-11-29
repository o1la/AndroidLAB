package com.example.shop.presentation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {

    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        Screen.Products,
        Screen.Cart
    )

    NavigationBar {
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon =  { Icon(screen.icon, contentDescription = null) },
                label = { Text(stringResource(id = screen.title)) },
                selected = selectedItem == index,
                onClick = {
                    navController.navigate(screen.route)
                    selectedItem = index
                }
            )
        }
    }
}