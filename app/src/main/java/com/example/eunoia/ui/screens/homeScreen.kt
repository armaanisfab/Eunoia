package com.example.eunoia.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eunoia.R
import com.example.eunoia.ui.components.CustomCard
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.SubheadingText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.screens.components.ImageCarousel

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        VerticalSpacer(space = 15.dp)
        HeadingText(text = "Welcome to Eunoia")
        VerticalSpacer(space = 15.dp)
        ImageCarousel()
        VerticalSpacer(space = 15.dp)
        SubheadingText(text = "Check in with yourself")
        VerticalSpacer(space = 15.dp)
        CustomCard(
            heading = "Welcome to Eunoia",
            subheading = "Your journey begins here",
            iconResId = R.drawable.smile_icon,
            onClick = {
                println("Card clicked!")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}