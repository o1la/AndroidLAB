package com.example.controller

import com.example.db.dao.daoCategory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.util.*

object CategoryController {

    suspend fun getAllCategories(call: ApplicationCall) {
        call.respond(daoCategory.allCategories())
    }

    suspend fun addCategory(call: ApplicationCall) {
        val formParameters = call.receiveParameters()
        val name = formParameters.getOrFail("name")
        val availability = formParameters.getOrFail("availability").toBoolean()
        val colorId = formParameters.getOrFail("colorId").toInt()
        val category = daoCategory.addNewCategory(name, availability, colorId)
        call.respondRedirect("/categories/${category?.id}")
    }

    suspend fun getCategoryById(call: ApplicationCall) {
        val id = call.parameters.getOrFail<Int>("id").toInt()
        call.respond(daoCategory.category(id) ?: HttpStatusCode.NotFound)    }

    suspend fun editCategory(call: ApplicationCall) {
        val id = call.parameters.getOrFail<Int>("id").toInt()
        call.respond(daoCategory.category(id) ?: HttpStatusCode.NotFound)
    }

    suspend fun postId(call: ApplicationCall) {
        val id = call.parameters.getOrFail<Int>("id").toInt()
        val formParameters = call.receiveParameters()
        when (formParameters.getOrFail("_action")) {
            "update" -> {
                val name = formParameters.getOrFail("name")
                val availability = formParameters.getOrFail("availability").toBoolean()
                val colorId = formParameters.getOrFail("colorId").toInt()
                daoCategory.editCategory(id, name, availability, colorId)
                call.respondRedirect("/categories/$id")
            }
            "delete" -> {
                daoCategory.deleteCategory(id)
                call.respondRedirect("/categories")
            }
        }
    }

}