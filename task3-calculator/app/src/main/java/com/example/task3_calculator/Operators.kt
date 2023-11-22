package com.example.task3_calculator

sealed class Operators(val symbol:String) {
    object Add : Operators("+")
    object Subtract : Operators("-")
    object Multiply : Operators("×")
    object Divide : Operators("÷")
    object Power : Operators("^")

}