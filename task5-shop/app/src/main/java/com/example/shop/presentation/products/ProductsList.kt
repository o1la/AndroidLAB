package com.example.shop.presentation.products

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shop.database.Product
import com.example.shop.presentation.PRODUCT_ID_KEY
import com.example.shop.presentation.Screen
import org.koin.compose.koinInject

@Composable
fun Products( navController: NavController) {

    val viewModel: ProductsViewModel = koinInject()
    val products by viewModel.products.collectAsState()

    LazyColumn(Modifier.padding(10.dp)) {
        items(products) { product ->
            ProductItem(product, navController)
        }
    }
}

@Composable
fun ProductItem(product: Product, navController: NavController) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(vertical = 5.dp)
            .clickable {
                navController.navigate(
                    Screen.ProductsDetails.route.replace(
                        "{$PRODUCT_ID_KEY}",
                        product.id.toString()
                    )
                )
            }
    ) {
        Box(
            Modifier.padding(10.dp)
        ) {
            Column {
                Row(
                    Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = product.name,
                        fontSize = 24.sp,
                    )
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    device = "id:Nexus 7"
)
@Composable
fun ProductsPreview() {
    Products(rememberNavController())
}