package com.example.payments.domain

import com.example.payments.domain.api.ApiService
import com.example.payments.model.PaymentDetails
import retrofit2.Response
import kotlinx.coroutines.delay

class MockApiService : ApiService {

    override suspend fun processPayment(paymentDetails: PaymentDetails): Response<Void> {
        delay(1000)

        return if (paymentDetails.cardNumber.startsWith("1")) {
            Response.success(null)
        } else {
            Response.error(400, okhttp3.ResponseBody.create(null, "Invalid card number"))
        }
    }
}
