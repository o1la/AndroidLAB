package com.example.network.ui.screens.product

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.network.models.Product
import org.koin.compose.koinInject


@Composable
fun ProductsList(categoryId: Int) {
    Log.d("PRODUCT-LIST", "categoryid: $categoryId")
    val viewModel: ProductViewModel = koinInject()
    val products by viewModel.products.collectAsState()

    val addProductView = remember { mutableStateOf(false) }

    LazyColumn(Modifier.padding(10.dp)) {
        items(products.filter { it.category == categoryId }) { product ->
            ProductItem(product)
        }
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(vertical = 5.dp)
            ) {
                Box(
                    Modifier
                        .padding(10.dp)
                        .clickable {
                            addProductView.value = !addProductView.value
                        }
                ) {
                    Row(
                        Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Add product to this category",
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
        if (addProductView.value) {
            item {
                AddProductToCategoryView(addProductView, categoryId, viewModel)
            }
        }
    }
}


@Composable
fun ProductItem(product: Product) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(vertical = 5.dp)
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
                    text = product.name,
                    fontSize = 24.sp,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text("$" + product.price, fontSize = 20.sp, fontStyle = FontStyle.Italic)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductToCategoryView(addProductView: MutableState<Boolean>, categoryId: Int, viewModel: ProductViewModel) {
    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = id, onValueChange = { id = it }, label = { Text("Id" )} )
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name" )} )
        OutlinedTextField(value = quantity, onValueChange = { quantity = it }, label = { Text("Quantity" )} )
        OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Price" )} )
        Row {
            IconButton(onClick = {
                val product = Product(id.toInt(), name, quantity.toInt(), price.toDouble(), categoryId)
                viewModel.addProduct(product,
                    onSuccess = {
                        addProductView.value = false
                        Toast.makeText(context, "Product added", Toast.LENGTH_SHORT).show()
                    },
                    onFailure = { errorMessage ->
                        Log.e("AddProduct", errorMessage)
                    }
                )
            }) {
                Icon(imageVector = Icons.Filled.Check, contentDescription = null)
            }
            IconButton(onClick = {
                id = ""
                name = ""
                quantity = ""
                price = ""
                category = ""
            }) {
                Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
            }
        }
    }
}


