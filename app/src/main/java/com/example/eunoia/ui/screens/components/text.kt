package com.example.eunoia.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp

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
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        ),
        textAlign = textAlign
    )
}

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
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        ),
        textAlign = textAlign
    )
}

@Composable
fun BoldText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        ),
        textAlign = textAlign
    )
}

@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal
        ),
        textAlign = textAlign
    )
}

@Composable
fun VerticalSpacer(space: Dp) {
    Spacer(modifier = Modifier.padding(vertical = space))
}

@Composable
fun HorizontalSpacer(space: Dp) {
    Spacer(modifier = Modifier.padding(horizontal = space))
}