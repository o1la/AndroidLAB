package com.example.network.ui.screens.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.network.models.Category
import org.koin.compose.koinInject

@Composable
fun CategoriesList(navController: NavController) {
    val viewModel: CategoryViewModel = koinInject()
    val categories by viewModel.categories.collectAsState(emptyList())

    LazyColumn(Modifier.padding(10.dp)) {
        items(categories) { category ->
            CategoryItem(category, navController)
        }
    }
}


@Composable
fun CategoryItem(category: Category, navController: NavController) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(vertical = 5.dp)
            .clickable {
                navController.navigate("products/${category.id}")
            }
    ) {
        Box(
            Modifier.padding(10.dp)
        ) {
            Row(
                Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = category.name,
                    fontSize = 24.sp,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    if (!category.availability) {
                        Text("Unavailable", fontSize = 20.sp, fontStyle = FontStyle.Italic)
                    }
                }
            }
        }
    }
}
