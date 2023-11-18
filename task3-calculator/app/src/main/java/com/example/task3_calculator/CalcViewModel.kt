package com.example.task3_calculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.StringBuilder

class CalcViewModel : ViewModel() {

    private val _result: MutableStateFlow<String> = MutableStateFlow<String>("0")
    val result: StateFlow<String> = _result.asStateFlow()

    private val _currentExpression = StringBuilder()

    private var _lastResult: Double? = null

    fun dispatch(action: ActionButton) {
        when (action) {
            is ActionButton.Clear -> clear()
            is ActionButton.Calculate -> calculate()
            is ActionButton.Delete -> deleteLast()
            is ActionButton.Number -> appendNumber(action.number)
            is ActionButton.Operator -> appendOperator(action.operation)
            else -> clear()
        }
    }

    private fun clear() {
        _currentExpression.clear()
        _lastResult = null
        _result.value = ""
    }

    private fun calculate() {
        try {
            val expression = ExpressionBuilder(_currentExpression.toString()).build()
            val result = expression.evaluate()

            _lastResult = result
            _currentExpression.clear()
            _currentExpression.append(result)

            _result.value = result.toString()
        } catch (e: Exception) {
            _result.value = "Error"
        }
    }

    private fun deleteLast() {
        if (_currentExpression.isNotEmpty()) {
            _currentExpression.deleteCharAt(_currentExpression.length - 1)
            _result.value = _currentExpression.toString()
        }
    }

    private fun appendNumber(number: String) {
        _currentExpression.append(number)
        _result.value = _currentExpression.toString()
    }

    private fun appendDecimal() {
        if (!_currentExpression.endsWith(".")) {
            _currentExpression.append(".")
            _result.value = _currentExpression.toString()
        }
    }

    private fun appendOperator(operator: Operators) {
        if (_currentExpression.isNotEmpty() && !_currentExpression.last().isDigit()) {
            _currentExpression.deleteCharAt(_currentExpression.length - 1)
        }
        _currentExpression.append(
            when (operator) {
                Operators.Add -> "+"
                Operators.Subtract -> "-"
                Operators.Multiply -> "*"
                Operators.Divide -> "/"
                Operators.Power -> "^"
            }
        )
        _result.value = _currentExpression.toString()
    }

}