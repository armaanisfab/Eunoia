package com.example.eunoia.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.theme.space1

@Composable
fun InsightsScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                VerticalSpacer(space = space1.dp)
                HeadingText(text = "Explore personalized AI insights")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInsightsScreen() {
    InsightsScreen()
}