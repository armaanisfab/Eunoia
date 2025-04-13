package com.example.eunoia.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import com.example.eunoia.ui.theme.ThemePurple2
import com.example.eunoia.ui.theme.ThemePurple3

val gradientColors = listOf(ThemePurple3, ThemePurple2)

@Composable
fun HeadingText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = Color.Black,
    size: Int = 35 // Default font size
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = size.sp, // Override size if specified
            fontWeight = FontWeight.Bold,
            color = color
        ),
        textAlign = textAlign
    )
}

@Composable
fun SubheadingText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = Color.Black,
    size: Int = 20 // Default font size
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = size.sp, // Override size if specified
            fontWeight = FontWeight.Bold,
            color = color
        ),
        textAlign = textAlign
    )
}

@Composable
fun GradientHeadingText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    size: Int = 35 // Default font size
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = size.sp, // Override size if specified
            fontWeight = FontWeight.Bold,
            lineHeight = 25.sp,
            brush = Brush.linearGradient(
                colors = gradientColors
            )
        ),
        textAlign = textAlign
    )
}

@Composable
fun GradientSubheadingText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    size: Int = 20 // Default font size
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = size.sp, // Override size if specified
            fontWeight = FontWeight.Bold,
            lineHeight = 25.sp,
            brush = Brush.linearGradient(
                colors = gradientColors
            )
        ),
        textAlign = textAlign
    )
}

@Composable
fun BoldText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = Color.Black,
    size: Int = 16 // Default font size
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = size.sp, // Override size if specified
            fontWeight = FontWeight.Bold,
            lineHeight = 25.sp,
            color = color
        ),
        textAlign = textAlign
    )
}

@Composable
fun NormalText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = Color.Black,
    size: Int = 16 // Default font size
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = size.sp, // Override size if specified
            fontWeight = FontWeight.Normal,
            lineHeight = 25.sp,
            color = color
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
