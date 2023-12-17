package com.example.database

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.database.models.Cart
import com.example.database.models.Category
import com.example.database.models.Product
import com.example.database.models.User
import com.example.database.ui.theme.DatabaseTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Firebase.firestore

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

        // Reading data
        db.collection("categories")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Firestore", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Firestore", "Error getting documents.", exception)



                setContent {
                    DatabaseTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            Greeting("Android")
                        }
                    }
                }
            }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DatabaseTheme {
        Greeting("Android")
    }
}