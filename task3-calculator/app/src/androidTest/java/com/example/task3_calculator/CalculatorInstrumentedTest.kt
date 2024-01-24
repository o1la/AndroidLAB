package com.example.task3_calculator

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CalculatorInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAdditionTwoNumbers() {
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("+").performClick()
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("5")
    }

    @Test
    fun testSubtractionTwoNumbers() {
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("-").performClick()
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("2")
    }

    @Test
    fun testMultiplicationTwoNumbers() {
        composeTestRule.onNodeWithTag("4").performClick()
        composeTestRule.onNodeWithTag("×").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("20")
    }

    @Test
    fun testDivisionTwoNumbers() {
        composeTestRule.onNodeWithTag("4").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("÷").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("9")
    }

    @Test
    fun testAdditionOfNegativeNumbers() {
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("+/-").performClick()
        composeTestRule.onNodeWithTag("+").performClick()
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("+/-").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-5")
    }

    @Test
    fun testSubtractionResultingInNegative() {
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("-").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-3")
    }

    @Test
    fun testMultiplicationResultingInNegative() {
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("+/-").performClick()
        composeTestRule.onNodeWithTag("×").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-10")
    }

    @Test
    fun testDivisionResultingInNegative() {
        composeTestRule.onNodeWithTag("4").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("+/-").performClick()
        composeTestRule.onNodeWithTag("÷").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-9")
    }

    @Test
    fun testAdditionThreeNumbers() {
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("+").performClick()
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("+").performClick()
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("8")
    }

    @Test
    fun testSubtractionThreeNumbers() {
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("-").performClick()
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("-").performClick()
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-1")
    }

    @Test
    fun testPercentageOfNumber() {
        composeTestRule.onNodeWithTag("1").performClick()
        composeTestRule.onNodeWithTag("%").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0.01")
    }

    @Test
    fun testPowerFunction() {
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("^").performClick()
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("8")
    }

    @Test
    fun testDecimalLogarithm() {
        composeTestRule.onNodeWithTag("1").performClick()
        composeTestRule.onNodeWithTag("log").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display")
            .assertTextEquals("0.0")
    }

    @Test
    fun testNaturalLogarithm() {
        composeTestRule.onNodeWithTag("1").performClick()
        composeTestRule.onNodeWithTag("ln").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0.0")
    }

    @Test
    fun testSquareRoot() {
        composeTestRule.onNodeWithTag("9").performClick()
        composeTestRule.onNodeWithTag("√").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("3.0")
    }

    @Test
    fun testAdditionDecimalNumbers() {
        composeTestRule.onNodeWithTag("1").performClick()
        composeTestRule.onNodeWithTag(",").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("+").performClick()
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag(",").performClick()
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("3.8")
    }

    @Test
    fun testSubtractionDecimalNumbers() {
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag(",").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("+").performClick()
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag(",").performClick()
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("7.7")
    }

    @Test
    fun testMultiplicationDecimalNumbers() {
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag(",").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("×").performClick()
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("5")
    }

    @Test
    fun testDivisionDecimalNumbers() {
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag(",").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("÷").performClick()
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("2.75")
    }

    @Test
    fun testPositiveToNegative() {
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("+/-").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-2")
    }

    @Test
    fun testNegativeToPositive() {
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("+/-").performClick()
        composeTestRule.onNodeWithTag("+/-").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("2")
    }

    @Test
    fun testSquareRootOfDecimal() {
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag(",").performClick()
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("√").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("1.5")
    }

    @Test
    fun testCombinationOfOperations() {
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("+").performClick()
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("×").performClick()
        composeTestRule.onNodeWithTag("4").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("-").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("15")
    }

    @Test
    fun testClearFunction() {
        composeTestRule.onNodeWithTag("1").performClick()
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("AC").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("")
    }

    @Test
    fun testDeleteSingleCharacter() {
        composeTestRule.onNodeWithTag("1").performClick()
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("del").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("1")
    }

    @Test
    fun testDivisionResultingInDecimal() {
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("÷").performClick()
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("2.5")
    }

    @Test
    fun testSquareOfNumber() {
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("^").performClick()
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("9")
    }

    @Test
    fun testSquareRootOfNegativeNumber() {
        composeTestRule.onNodeWithTag("4").performClick()
        composeTestRule.onNodeWithTag("+/-").performClick()
        composeTestRule.onNodeWithTag("√").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("NaN")
    }

    @Test
    fun testChangeSignAfterOperation() {
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("+").performClick()
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("+/-").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-5")
    }

    @Test
    fun testAddingNegativeNumbers() {
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("+/-").performClick()
        composeTestRule.onNodeWithTag("+").performClick()
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("+/-").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-5")
    }

    @Test
    fun testMultiplicationWithNegativeResult() {
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("+/-").performClick()
        composeTestRule.onNodeWithTag("×").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-10")
    }

    @Test
    fun testDivisionWithNegativeResult() {
        composeTestRule.onNodeWithTag("1").performClick()
        composeTestRule.onNodeWithTag("0").performClick()
        composeTestRule.onNodeWithTag("+/-").performClick()
        composeTestRule.onNodeWithTag("÷").performClick()
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-5")
    }

    @Test
    fun testPercentageOfNegativeNumber() {
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("+/-").performClick()
        composeTestRule.onNodeWithTag("%").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-0.05")
    }

    @Test
    fun testDivisionByNegativeNumber() {
        composeTestRule.onNodeWithTag("6").performClick()
        composeTestRule.onNodeWithTag("÷").performClick()
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("+/-").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-2")
    }

    @Test
    fun testPercentageOfLargeNumber() {
        composeTestRule.onNodeWithTag("1").performClick()
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("4").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("%").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("123.45")
    }

    @Test
    fun testPowerOfZero() {
        composeTestRule.onNodeWithTag("0").performClick()
        composeTestRule.onNodeWithTag("^").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0")
    }

    @Test
    fun testZeroPowerOfNumber() {
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("^").performClick()
        composeTestRule.onNodeWithTag("0").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("1")
    }

    @Test
    fun testDivisionByOne() {
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("÷").performClick()
        composeTestRule.onNodeWithTag("1").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("5")
    }

    @Test
    fun testSquareRootOfOne() {
        composeTestRule.onNodeWithTag("1").performClick()
        composeTestRule.onNodeWithTag("√").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("1.0")
    }

    @Test
    fun testZeroAddition() {
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("+").performClick()
        composeTestRule.onNodeWithTag("0").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("5")
    }

    @Test
    fun testZeroSubtraction() {
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("-").performClick()
        composeTestRule.onNodeWithTag("0").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("5")
    }

    @Test
    fun testZeroMultiplication() {
        composeTestRule.onNodeWithTag("0").performClick()
        composeTestRule.onNodeWithTag("×").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0")
    }

    @Test
    fun testZeroDivision() {
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("÷").performClick()
        composeTestRule.onNodeWithTag("0").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0")
    }

    @Test
    fun testLogarithmOfTen() {
        composeTestRule.onNodeWithTag("10").performClick()
        composeTestRule.onNodeWithTag("log").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("1.0")
    }

    @Test
    fun testZeroAsFirstDigit() {
        composeTestRule.onNodeWithTag("0").performClick()
        composeTestRule.onNodeWithTag("-").performClick()
        composeTestRule.onNodeWithTag("5").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-5")
    }

    @Test
    fun testPowerOfFraction() {
        composeTestRule.onNodeWithTag("0").performClick()
        composeTestRule.onNodeWithTag(",").performClick()
        composeTestRule.onNodeWithTag("1").performClick()
        composeTestRule.onNodeWithTag("^").performClick()
        composeTestRule.onNodeWithTag("2").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0.01")
    }

    @Test
    fun testDivisionResultingInLongDecimal() {
        composeTestRule.onNodeWithTag("1").performClick()
        composeTestRule.onNodeWithTag("÷").performClick()
        composeTestRule.onNodeWithTag("3").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0.33333333")
    }

    @Test
    fun testMultiplicationWithLargeNumbers() {
        composeTestRule.onNodeWithTag("9").performClick()
        composeTestRule.onNodeWithTag("9").performClick()
        composeTestRule.onNodeWithTag("9").performClick()
        composeTestRule.onNodeWithTag("×").performClick()
        composeTestRule.onNodeWithTag("9").performClick()
        composeTestRule.onNodeWithTag("9").performClick()
        composeTestRule.onNodeWithTag("9").performClick()
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("998001")
    }

    @Test
    fun testSubtractionWithLargeNumbers() {
        composeTestRule.onNodeWithTag("1").performClick()
        for (i in 0..5) {
            composeTestRule.onNodeWithTag("0").performClick()
        }
        composeTestRule.onNodeWithTag("-").performClick()
        composeTestRule.onNodeWithTag("9").performClick()
        for (i in 0..2) {
            composeTestRule.onNodeWithTag("9").performClick()
        }
        composeTestRule.onNodeWithTag("=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("1")
    }

    @Test
    fun testSquareRootOfLargeNumber() {
        composeTestRule.onNodeWithTag("1").performClick()
        for (i in 0..5) {
            composeTestRule.onNodeWithTag("0").performClick()
        }
        composeTestRule.onNodeWithTag("√").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("100.0")
    }

}
