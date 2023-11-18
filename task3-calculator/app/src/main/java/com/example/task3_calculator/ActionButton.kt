package com.example.task3_calculator

import androidx.compose.ui.graphics.Color
import com.example.task3_calculator.ui.theme.darkGray
import com.example.task3_calculator.ui.theme.lightGray

sealed class ActionButton(val symbol: String, val buttonColor: Color = darkGray) {
    data class Number(val number: String) : ActionButton(number, lightGray)
    data class Operator(val operation: Operators) : ActionButton(operation.symbol, darkGray)

    object Clear : ActionButton("AC")
    object Delete : ActionButton("del")
    object Decimal : ActionButton(",")
    object Percentage : ActionButton("%")
    object Log : ActionButton("log")
    object Ln : ActionButton("ln")
    object Sign : ActionButton("+/-")
    object Root : ActionButton("^")
    object Calculate : ActionButton("=")
}

sealed class Operators(val symbol:String) {
    object Add : Operators("+")
    object Subtract : Operators("-")
    object Multiply : Operators("×")
    object Divide : Operators("÷")
    object Power : Operators("^")

}
