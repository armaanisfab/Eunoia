package com.example.eunoia.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.eunoia.R
import com.example.eunoia.ui.components.BoldText
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.SubheadingText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.theme.ThemePurple2
import com.example.eunoia.ui.theme.ThemePurple3
import com.example.eunoia.ui.theme.space1
import com.example.eunoia.ui.theme.space2

@Composable
fun MoodScreen(navController: NavController) {
    var sliderValue by remember { mutableStateOf(1f) }
    var sliderTouched by remember { mutableStateOf(false) }

    val moodDescriptions = listOf(
        "I'm feeling terrible.",
        "I'm not feeling great.",
        "I'm feeling okay.",
        "I'm feeling good!",
        "I'm feeling great!"
    )
    val moodColors = listOf(
        Color.Red, // Terrible
        Color(0xFFFF8900), // Not great
        Color(0xFFFFD54F), // Okay
        Color(0xFFAED581), // Good
        Color(0xFF2EC400) // Great
    )
    val moodImages = listOf(
        R.drawable.face1,
        R.drawable.face2,
        R.drawable.face3,
        R.drawable.face4,
        R.drawable.face5
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top section: Back icon and heading
        Column {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back Icon",
                tint = Color.Black,
                modifier = Modifier
                    .clickable { navController.navigate(Routes.Home.route) }
            )
            VerticalSpacer(space = space2.dp)
            HeadingText(text = "Log your mood")
        }

        // Middle section: Image and description
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageToShow =
                if (!sliderTouched) R.drawable.face0 else moodImages[sliderValue.toInt() - 1]
            val textToShow =
                if (!sliderTouched) "How are you feeling?" else moodDescriptions[sliderValue.toInt() - 1]
            val textColor =
                if (!sliderTouched) Color.Black else moodColors[sliderValue.toInt() - 1]

            Image(
                painter = painterResource(id = imageToShow),
                contentDescription = "Mood Image",
                modifier = Modifier.size(250.dp)
            )
            VerticalSpacer(space = space1.dp)
            SubheadingText(text = textToShow, color = textColor)
        }

        // Bottom section: Slider and button
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Very bad",
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
                Text(
                    text = "Very good",
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }

            Slider(
                value = sliderValue,
                onValueChange = {
                    sliderValue = it
                    sliderTouched = true // Set to true when the slider is interacted with
                },
                onValueChangeFinished = {
                    sliderTouched = true // Also set to true when the user releases the slider
                },
                valueRange = 1f..5f,
                steps = 3,
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                colors = SliderDefaults.colors(
                    activeTrackColor = ThemePurple2,
                    thumbColor = ThemePurple3
                )
            )
            Button(
                onClick = { navController.navigate(Routes.Home.route) },
                enabled = sliderTouched, // Button only enabled once the slider is touched
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (sliderTouched) ThemePurple3 else Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Dial it in")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMoodScreen() {
    val mockNavController = rememberNavController()
    MoodScreen(navController = mockNavController)
}
