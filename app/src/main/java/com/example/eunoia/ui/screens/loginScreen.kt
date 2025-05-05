package com.example.eunoia.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.eunoia.feature.auth.data.model.AuthState
import com.example.eunoia.feature.auth.presentation.viewmodel.AuthViewModel
import com.example.eunoia.ui.components.GradientHeadingText
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.theme.ThemePurple1
import com.example.eunoia.ui.theme.ThemePurple2
import com.example.eunoia.ui.theme.ThemePurple3
import com.example.eunoia.ui.theme.space1
import com.example.eunoia.ui.theme.space2

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel = hiltViewModel()) {
    // Text states for username and password
//    var username by remember { mutableStateOf("") }
    // ala ooj
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    // ala ooj
    val authState = authViewModel.userState.value

    // ala ooj
    LaunchedEffect(key1 = authState) {
        if (authState is AuthState.Authenticated) {
            navController.navigate(Routes.Home.route) {
                popUpTo(Routes.Login.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HeadingText(text = "Welcome to")
        GradientHeadingText(text = "Balls", size = 62)
        VerticalSpacer(space = space1.dp)

        // Username TextField
//        TextField(
//            value = username,
//            onValueChange = { username = it },
//            label = { Text("Username") },
//            modifier = Modifier
//                .fillMaxWidth(0.8f),
//            colors = TextFieldDefaults.colors(
//                unfocusedContainerColor = ThemePurple1, // Color when no input or not focused
//                focusedContainerColor = ThemePurple2,  // Color when focused or active
//                unfocusedTextColor = Color.Black,
//                focusedTextColor = Color.Black
//            )
//        )
//        VerticalSpacer(space = space2.dp)

        // ala ooj
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = ThemePurple1,
                focusedContainerColor = ThemePurple2,
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black
            )
        )
        VerticalSpacer(space = space2.dp)

        // Password TextField
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth(0.8f),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = ThemePurple1, // Color when no input or not focused
                focusedContainerColor = ThemePurple2,  // Color when focused or active
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black
            ),
            visualTransformation = PasswordVisualTransformation() // Mask input for password
        )
        VerticalSpacer(space = space1.dp)

        // ala ooj
        if (authState is AuthState.Loading) {
            CircularProgressIndicator()
            VerticalSpacer(space = space1.dp)
        }

        // ala ooj
        if (authState is AuthState.Error) {
            Text(text = authState.message, color = Color.Red)
            VerticalSpacer(space = space1.dp)
        }

        // Login Button
        Button(
            onClick = { navController.navigate(Routes.Home.route) },
            // ala ooj
            enabled = email.isNotEmpty() && password.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (email.isNotEmpty() && password.isNotEmpty()) ThemePurple3 else Color.Gray,
                contentColor = Color.White
            )
        ) {
            Text(text = "Login")
        }
        VerticalSpacer(space = 4.dp)
        Text(text = "New user? Sign up")

        // ala ooj
        Button(
            onClick = { authViewModel.signUp(email, password) },
            enabled = email.isNotEmpty() && password.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor =
                if (email.isNotEmpty() && password.isNotEmpty()) ThemePurple3 else Color.Gray,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Sign Up")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val mockNavController = rememberNavController()
    LoginScreen(navController = mockNavController)
}
