package com.example.payments.repository

import com.example.payments.domain.MockApiService
import com.example.payments.domain.api.ApiService
import com.example.payments.model.PaymentDetails
import retrofit2.Response

class PaymentRepository(private val apiService: ApiService = MockApiService()) {
    suspend fun processPayment(paymentModel: PaymentDetails): Response<Void> {
        return apiService.processPayment(paymentModel)
    }

}