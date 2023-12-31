package com.example.shop.presentation.products.details

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shop.R
import org.koin.compose.koinInject

@Composable
fun ProductDetails(productId: Long) {

    val viewModel: ProductDetailsViewModel = koinInject()

    LaunchedEffect(productId) {
        viewModel.fetchProductDetails(productId)
    }

    val product = viewModel.product.collectAsState().value
    var quantity by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(text = product?.name ?: "", fontSize = 64.sp, modifier = Modifier.padding(bottom = 50.dp))
        Text(text = product?.description ?: "", fontSize = 24.sp, modifier = Modifier.padding(bottom = 10.dp), fontStyle = FontStyle.Italic)
        Text(text = "${product?.price?.times(quantity) ?: 0} USD", fontSize = 40.sp, modifier = Modifier.padding(bottom = 20.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = { if (quantity > 1) quantity-- },
                modifier = Modifier.size(64.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, Modifier.size(30.dp))
            }

            Text("$quantity", fontSize = 30.sp)

            IconButton(
                onClick = { quantity++ },
                modifier = Modifier.size((64.dp))
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, Modifier.size(30.dp))
            }
        }
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if (product != null) {
                        viewModel.addToCart(product, quantity)
                    }
                },
                modifier = Modifier
            ) {
                Text(stringResource(R.string.add_to_cart), color = Color.White, fontSize = 22.sp, modifier = Modifier.padding(5.dp))
            }
        }

    }

    val context = LocalContext.current
    val toastMessage by viewModel.toastMessage.collectAsState()
    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearToastMessage()
        }
    }
}
