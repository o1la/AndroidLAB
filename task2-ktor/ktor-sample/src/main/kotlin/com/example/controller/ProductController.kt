package com.example.controller

import com.example.db.dao.dao
import com.example.model.Product
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.util.*


object ProductController {
    suspend fun getAllProducts(call: ApplicationCall) {
        call.respond(dao.allProducts())
    }

    suspend fun addProduct(call: ApplicationCall) {
        try {
            val productData = call.receive<Product>()

            dao.addNewProduct(
                productData.id,
                productData.name,
                productData.quantity,
                productData.price,
                productData.category
            )

            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Error adding product: ${e.message}")
        }
    }


    suspend fun getProductById(call: ApplicationCall) {
        val id = call.parameters.getOrFail<Int>("id").toInt()
        call.respond(dao.product(id) ?: HttpStatusCode.NotFound)    }

    suspend fun editProduct(call: ApplicationCall) {
        val id = call.parameters.getOrFail<Int>("id").toInt()
        call.respond(dao.product(id) ?: HttpStatusCode.NotFound)
    }

    suspend fun postId(call: ApplicationCall) {
        val id = call.parameters.getOrFail<Int>("id").toInt()
        val formParameters = call.receiveParameters()
        when (formParameters.getOrFail("_action")) {
            "update" -> {
                val name = formParameters.getOrFail("name")
                val quantity = formParameters.getOrFail("quantity").toInt()
                val price = formParameters.getOrFail("price").toDouble()
                val categoryId = formParameters.getOrFail("categoryId").toInt()
                dao.editProduct(id, name, quantity, price, categoryId)
                call.respondRedirect("/products/$id")
            }
            "delete" -> {
                dao.deleteProduct(id)
                call.respondRedirect("/products")
            }
        }
    }
}
