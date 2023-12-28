package com.example.network.ui.screens.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.network.models.Category

@Composable
fun CategoriesList(navController: NavController, viewModel: CategoryViewModel) {
    val categories by viewModel.categories.collectAsState(emptyList())

    var addProductView = remember { mutableStateOf(false) }

    LazyColumn(Modifier.padding(10.dp)) {
        items(categories) { category ->
            CategoryItem(category, navController)
        }
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        addProductView.value = true
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Add product to this category")
            }
        }
        if (addProductView.value) {
            item {
                addCategoryView()
            }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addCategoryView() {
    var name by remember { mutableStateOf("name") }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = name, onValueChange = { name = it })
    }
}

@Preview
@Composable
fun previewAddCategory() {
    Box(modifier = Modifier.fillMaxSize()) {
        addCategoryView()
    }
}
