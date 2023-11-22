package com.example.task3_calculator

data class CalculatorState(
    val number1: String = "",
    val number2: String = "",
    val operation: Operators? = null
)
