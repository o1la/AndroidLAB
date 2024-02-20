package com.example.payments.model

data class PaymentDetails(
    val cardNumber: String,
    val cardholdersName: String,
    val expiryDate: String,
    val cvv: String
)