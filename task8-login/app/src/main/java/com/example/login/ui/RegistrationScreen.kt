package com.example.login.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login.R
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }
    var registrationError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.create_account), fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = it.isBlank()
                            },
            label = { Text(stringResource(R.string.email)) },
            isError = emailError,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (emailError) {
            Text(stringResource(R.string.email_cannot_be_empty), color = Color.Red)
        }

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = it.isBlank()
            },
            label = { Text(stringResource(R.string.password)) },
            isError = passwordError,
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (passwordError) {
            Text(stringResource(R.string.password_cannot_be_empty), color = Color.Red)
        }

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                confirmPasswordError = it != password
            },
            label = { Text(stringResource(R.string.confirm_password)) },
            isError = confirmPasswordError,
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        if (confirmPasswordError) {
            Text(stringResource(R.string.passwords_do_not_match), color = Color.Red)
        }


        if (registrationError != null) {
            Text(registrationError!!, color = Color.Red)
        }
        Button(
            onClick = {
                if (email.isNotBlank() && password.isNotBlank() && password == confirmPassword) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            navController.navigate("login")
                        } else {
                            registrationError = task.exception?.message
                        }
                    }
            } },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.sign_up), fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationPreview() {
    RegistrationScreen(navController = rememberNavController())
}
