package com.example.eunoia.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

// Heading Component
@Composable
fun HeadingText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = 24.sp, // Customize the size
            fontWeight = FontWeight.Bold // Customize the weight
        ),
        textAlign = textAlign
    )
}

// Subheading Component
@Composable
fun SubheadingText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = 18.sp, // Customize the size
            fontWeight = FontWeight.Medium // Customize the weight
        ),
        textAlign = textAlign
    )
}