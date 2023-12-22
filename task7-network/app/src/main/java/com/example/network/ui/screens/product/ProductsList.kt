package com.example.network.ui.screens.product

import android.util.Log
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
import com.example.network.models.Product
import org.koin.compose.koinInject


@Composable
fun ProductsList(categoryId: Int) {
    Log.d("PRODUCT-LIST", "categoryid: $categoryId")
    val viewModel: ProductViewModel = koinInject()
    val products by viewModel.products.collectAsState()

    LazyColumn(Modifier.padding(10.dp)) {
        items(products.filter { it.category == categoryId }) { product ->
            ProductItem(product)
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
