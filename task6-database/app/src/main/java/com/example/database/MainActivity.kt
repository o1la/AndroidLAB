package com.example.database

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.database.models.Cart
import com.example.database.models.Category
import com.example.database.models.Product
import com.example.database.models.User
import com.example.database.ui.theme.DatabaseTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DatabaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Button(onClick = {
                                db.collection("categories")
                                    .get()
                                    .addOnSuccessListener { result ->
                                        for (document in result) {
                                            Log.d("Firestore", "${document.id} => ${document.data}")
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.w("Firestore", "Error getting documents.", exception)
                                    }
                            }) {
                                Text(text = "Get Category data in LogCat")
                            }

                            Button(onClick = {
                                db.collection("products")
                                    .get()
                                    .addOnSuccessListener { result ->
                                        for (document in result) {
                                            Log.d("Firestore", "${document.id} => ${document.data}")
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.w("Firestore", "Error getting documents.", exception)
                                    }
                            }) {
                                Text(text = "Get Products data in LogCat")
                            }

                            Button(onClick = {
                                db.collection("users")
                                    .get()
                                    .addOnSuccessListener { result ->
                                        for (document in result) {
                                            Log.d("Firestore", "${document.id} => ${document.data}")
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.w("Firestore", "Error getting documents.", exception)
                                    }
                            }) {
                                Text(text = "Get Users data in LogCat")
                            }

                            Button(onClick = {
                                db.collection("cart")
                                    .get()
                                    .addOnSuccessListener { result ->
                                        for (document in result) {
                                            Log.d("Firestore", "${document.id} => ${document.data}")
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.w("Firestore", "Error getting documents.", exception)
                                    }
                            }) {
                                Text(text = "Get Cart data in Logcat")
                            }
                        }
                    }

                }
            }
        }

        val category = Category(
            id = 100,
            name = "Clothes"
        )

        db.collection("categories").document(category.id.toString()).set(category)
            .addOnSuccessListener {
                Log.d("Firestore", "DocumentSnapshot added")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }

        val product = Product(
            id = 200,
            name = "Jacket",
            price = 59.99,
            description =  "Winter jacket",
            category = "Clothes"
        )

        db.collection("categories").document(category.id.toString())
            .collection("products").document(product.id.toString()).set(product)
            .addOnSuccessListener {
                Log.d("Firestore", "DocumentSnapshot added")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }

        val user = User(
            id = 300,
            first = "Aleksandra",
            last = "Grygorczyk",
            nickname = "Ola"
        )

        db.collection("users").document(user.id.toString()).set(user)
            .addOnSuccessListener {
                Log.d("Firestore", "DocumentSnapshot added")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }

        val cart = Cart(
            id = 400,
            products = listOf(1),
            total = 59.99,
            userId = 300
        )

        db.collection("users").document(user.id.toString())
            .collection("cart")
            .document(cart.id.toString()).set(cart)
            .addOnSuccessListener {
                Log.d("Firestore", "DocumentSnapshot added")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }
    }
}
