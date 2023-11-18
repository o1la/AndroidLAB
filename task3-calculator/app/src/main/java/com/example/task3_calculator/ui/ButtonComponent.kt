package com.example.task3_calculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task3_calculator.ui.theme.Task3calculatorTheme
import com.example.task3_calculator.ui.theme.purple

@Composable
fun ButtonComponent(
    symbol: String,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier
    ) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
            .background(color)
            .then(modifier)
            .fillMaxSize()
    ) {
        Text(text = symbol, fontSize = 30.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonComponentPreview() {
    Task3calculatorTheme {
        ButtonComponent(symbol = "+", color = purple, onClick = {}, modifier = Modifier.size(70.dp))
    }
}