package com.example.payments.domain.api

import com.example.payments.model.PaymentDetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("processPayment")
    suspend fun processPayment(@Body paymentDetails: PaymentDetails): Response<Void>
}