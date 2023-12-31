package com.example.plugins

import com.example.controller.CategoryController
import com.example.controller.ProductController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/products") {
            get {
                ProductController.getAllProducts(call)
            }
            post {
               ProductController.addProduct(call)
            }
            get("{id}") {
                ProductController.getProductById(call)
            }
            get("{id}/edit") {
                ProductController.editProduct(call)
            }
            post("{id}") {
                ProductController.postId(call)
            }
        }

        route("/categories") {
            get {
                CategoryController.getAllCategories(call)
            }
            post {
                CategoryController.addCategory(call)
            }
            get("{id}") {
                CategoryController.getCategoryById(call)
            }
            get("{id}/edit") {
                CategoryController.editCategory(call)
            }
            post("{id}") {
                CategoryController.postId(call)
            }
        }
    }
}
