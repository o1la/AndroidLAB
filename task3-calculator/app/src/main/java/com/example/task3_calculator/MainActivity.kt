package com.example.task3_calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.task3_calculator.ui.ButtonComponent
import com.example.task3_calculator.ui.theme.Task3calculatorTheme
import com.example.task3_calculator.ui.theme.white

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Task3calculatorTheme {
                CalculatorScreen()
            }
        }
    }
}

@Composable
private fun CalculatorScreen() {

    val viewModel = viewModel<CalcViewModel>()
    val state = viewModel.state

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 30.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column {
                Text(
                    text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                    overflow = TextOverflow.Visible,
                    maxLines = 2,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth(),
                    color = white,
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 80.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                CalcButtonLayout(viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalcScreenPreview() {
    Task3calculatorTheme {
        CalculatorScreen()
    }
}

@Composable
fun CalcButtonLayout(viewModel: CalcViewModel) {
    val buttons = listOf(
        ActionButton.Clear,
        ActionButton.Log,
        ActionButton.Ln,
        ActionButton.Percentage,
        ActionButton.Delete,
        ActionButton.Number(7),
        ActionButton.Number(8),
        ActionButton.Number(9),
        ActionButton.Operator(Operators.Power),
        ActionButton.Operator(Operators.Divide),
        ActionButton.Number(4),
        ActionButton.Number(5),
        ActionButton.Number(6),
        ActionButton.SquareRoot,
        ActionButton.Operator(Operators.Multiply),
        ActionButton.Number(1),
        ActionButton.Number(2),
        ActionButton.Number(3),
        ActionButton.Calculate,
        ActionButton.Operator(Operators.Add),
        ActionButton.Number(0),
        ActionButton.Number(0),
        ActionButton.Decimal,
        ActionButton.Sign,
        ActionButton.Operator(Operators.Subtract)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        items(buttons) {
            ButtonComponent(
                symbol = it.symbol,
                color = it.buttonColor,
                onClick = { viewModel.dispatch(it) },
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(6.dp)
            )
        }
    }
}
