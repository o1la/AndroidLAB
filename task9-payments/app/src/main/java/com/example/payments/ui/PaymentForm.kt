package com.example.payments.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.payments.model.PaymentDetails
import com.example.payments.repository.PaymentRepository
import com.example.payments.ui.theme.PaymentsTheme
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentForm() {
    // mock api responds with success if card number starts with '1'
    var cardNumber by remember { mutableStateOf("1234567812345678") }
    var cardholdersName by remember { mutableStateOf("Aleksandra") }
    var expiryDate by remember { mutableStateOf("11/24") }
    var cvv by remember { mutableStateOf("123") }

    var cardNumberError by remember { mutableStateOf(false) }
    var cardholdersNameError by remember { mutableStateOf(false) }
    var expiryDateError by remember { mutableStateOf(false) }
    var cvvError by remember { mutableStateOf(false) }

    var paymentResult by remember { mutableStateOf<Response<Void>?>(null) }
    var triggerPayment by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(triggerPayment) {
        if (triggerPayment) {
            val paymentDetails = PaymentDetails(
                cardNumber = cardNumber,
                cardholdersName = cardholdersName,
                expiryDate = expiryDate,
                cvv = cvv
            )

            val paymentRepository = PaymentRepository()
            try {
                paymentResult = paymentRepository.processPayment(paymentDetails)
            } catch (e: Exception) {
                Log.d("PaymentForm", "Error processing payment", e)
            }

            triggerPayment = false

            if (paymentResult != null) {
                if (paymentResult!!.isSuccessful) {
                    Toast.makeText(context, "Płatność zakończona sukcesem", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Płatność nie powiodła się", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = cardNumber,
            onValueChange = { cardNumber = it },
            label = { Text("Card number") },
            isError = cardNumberError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = cardholdersName,
            onValueChange = { cardholdersName = it },
            label = { Text("Cardholder's name") },
            isError = cardholdersNameError,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            OutlinedTextField(
                value = expiryDate,
                onValueChange = { expiryDate = it },
                label = { Text("Expiration date") },
                isError = expiryDateError
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = cvv,
                onValueChange = { cvv = it },
                label = { Text("CVV") },
                isError = cvvError
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                cardNumberError = !isValidCardNumber(cardNumber)
                cardholdersNameError = !isValidCardholderName(cardholdersName)
                expiryDateError = !isValidExpiryDate(expiryDate)
                cvvError = !isValidCvv(cvv)

                if (!cardNumberError && !cardholdersNameError && !expiryDateError && !cvvError) {
                    triggerPayment = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Pay")
        }

    }
}

fun isValidCardNumber(cardNumber: String): Boolean {
    return cardNumber.length == 16
}

fun isValidCardholderName(name: String): Boolean {
    return name.isNotEmpty()
}

fun isValidExpiryDate(expiryDate: String): Boolean {
    if (!expiryDate.matches(Regex("\\d{2}/\\d{2}"))) {
        return false
    }

    val sdf = SimpleDateFormat("MM/yy", Locale.getDefault())
    sdf.isLenient = false

    return try {
        val expiration = sdf.parse(expiryDate) ?: return false
        val currentDate = Calendar.getInstance().time
        expiration.after(currentDate)
    } catch (e: Exception) {
        false
    }
}

fun isValidCvv(cvv: String): Boolean {
    return cvv.length == 3
}



@Preview(showBackground = true)
@Composable
fun PaymentFormPreview() {
    PaymentsTheme {
        PaymentForm()
    }
}
