package com.example.eunoia.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eunoia.R
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.SubheadingText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.components.icon_heading_subheading
import com.example.eunoia.ui.components.number_text_number_text
import com.example.eunoia.ui.screens.components.ImageCarousel
import com.example.eunoia.ui.theme.space1
import com.example.eunoia.ui.theme.space2

@Composable
fun ProgressScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(25.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            VerticalSpacer(space = space1.dp)
            HeadingText(text = "You're doing great!")
            VerticalSpacer(space = space1.dp)
            number_text_number_text(
                number1 = 5,
                text1 = "day streak",
                number2 = 9,
                text2 = "day high score",
                onClick = {
                    println("Card clicked!")
                }
            )
            VerticalSpacer(space = space1.dp)
            SubheadingText(text = "Your mood trend")
            VerticalSpacer(space = space2.dp)
            val progressScreenImageCarousel = listOf(
                R.drawable.graph
            )
            ImageCarousel(imageResources = progressScreenImageCarousel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProgressScreen() {
    ProgressScreen()
}