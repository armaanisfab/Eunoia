package com.example.eunoia.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.SubheadingText

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        HeadingText(text = "Welcome to Eunoia")
        SubheadingText(text = "Your journey starts here")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}