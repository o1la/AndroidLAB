package com.example.task3_calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.ln
import kotlin.math.log
import kotlin.math.sqrt

class CalcViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())

    fun dispatch(action: ActionButton) {
        when (action) {
            is ActionButton.Clear -> state = CalculatorState()
            is ActionButton.Calculate -> calculate()
            is ActionButton.Delete -> deleteLast()
            is ActionButton.Number -> appendNumber(action.number)
            is ActionButton.Operator -> appendOperator(action.operation)
            is ActionButton.Decimal -> appendDecimal()
            is ActionButton.Log -> logNumber()
            is ActionButton.Ln -> lnNumber()
            is ActionButton.Percentage -> percentageNumber()
            is ActionButton.Sign -> appendSign()
            is ActionButton.SquareRoot -> sqrtNumber()
        }
    }

    private fun sqrtNumber() {
        if (state.number1.isNotBlank()) {
            state = state.copy(
                number1 = sqrt(state.number1.toDouble()).toString()
            )
        }
    }

    private fun appendSign() {
        if (state.operation == null && state.number1.contains("-") && state.number1.isNotBlank()) {
            state = state.copy(
                number1 = state.number1.removePrefix("-")
            )
            return
        } else if (state.operation == null && !state.number1.contains("-") && state.number1.isNotBlank()) {
            state = state.copy(
                number1 = "-" + state.number1
            )
            return
        } else if (state.number2.contains("-") && state.number2.isNotBlank()) {
            state = state.copy(
                number2 = state.number2.removePrefix("-")
            )
        } else if (!state.number2.contains("-") && state.number2.isNotBlank()) {
            state = state.copy(
                number2 = "-" + state.number2
            )
        }
    }

    private fun percentageNumber() {
        if (state.number1.isNotBlank()) {
            state = state.copy(
                number1 = (state.number1.toDouble() / 100).toString()
            )
        }
    }

    private fun lnNumber() {
        if (state.number1.isNotBlank()) {
            state = state.copy(
                number1 = ln(state.number1.toDouble()).toString()
            )
        }
    }

    private fun logNumber() {
        if (state.number1.isNotBlank()) {
            state = state.copy(
                number1 = log(state.number1.toDouble(), 10.0).toString()
            )
        }
    }


    private fun calculate() {
        val number1 = state.number1.toBigDecimalOrNull()
        val number2 = state.number2.toBigDecimalOrNull()
        val context = MathContext(8)
        if (number1 != null && number2 != null) {
            kotlin.runCatching {
                when (state.operation) {
                    is Operators.Add -> number1 + number2
                    is Operators.Subtract -> number1 - number2
                    is Operators.Multiply -> number1 * number2
                    is Operators.Divide -> if (number2.compareTo(BigDecimal.ZERO) != 0) {
                        number1.divide(number2, context)
                    } else {
                        BigDecimal.ZERO
                    }

                    is Operators.Power -> number1.pow(number2.toInt())
                    null -> return
                }
            }.onSuccess { result->
                state = state.copy(
                    number1 = result.stripTrailingZeros().toPlainString(),
                    number2 = "",
                    operation = null
                )
            }.onFailure {
                BigDecimal.ZERO
            }

        }
    }

    private fun deleteLast() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )

            state.operation != null -> state = state.copy(
                operation = null
            )

            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun appendNumber(number: Int) {
        if (state.operation == null) {
            if (state.number1.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if (state.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }

    private fun appendDecimal() {
        if (state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank()) {
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        } else if (!state.number2.contains(".") && state.number2.isNotBlank()) {
            state = state.copy(
                number2 = state.number2 + "."
            )
        }
    }

    private fun appendOperator(operation: Operators) {
        if (state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    companion object {
        private const val MAX_NUM_LENGTH = 5
    }

}