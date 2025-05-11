package com.example.eunoia.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.eunoia.R
import com.example.eunoia.feature.journal.presentation.viewmodel.JournalViewModel
import com.example.eunoia.feature.moodlog.data.model.MoodLog
import com.example.eunoia.feature.moodlog.presentation.viewmodel.MoodLogViewModel
import com.example.eunoia.feature.profile.presentation.viewmodel.ProfileViewModel
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.SubheadingText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.theme.ThemePurple2
import com.example.eunoia.ui.theme.ThemePurple3

@Composable
fun MoodScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    journalViewModel: JournalViewModel = hiltViewModel(),
    moodLogViewModel: MoodLogViewModel = hiltViewModel()
) {
    val profile by profileViewModel.profileState.collectAsState()
    val userId = profile?.id

    LaunchedEffect(key1 = userId) {
        if (userId != null) {
            journalViewModel.fetchOrCreateUserJournal(userId)
        }
    }

    val isJournalLoading by journalViewModel.isLoading.collectAsState()
    val journalId = journalViewModel.journalState.collectAsState().value?.id

    if (isJournalLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LaunchedEffect(journalId) {
            if (journalId != null) {
                moodLogViewModel.fetchMoodLogs(journalId)
            }
        }

        var sliderValue by remember { mutableFloatStateOf(1f) }
        var sliderTouched by remember { mutableStateOf(false) }

        val moodDescriptions = listOf(
            "I'm feeling terrible.",
            "I'm not feeling great.",
            "I'm feeling okay.",
            "I'm feeling good!",
            "I'm feeling great!"
        )
        val moodColors = listOf(
            Color.Red,             // Terrible
            Color(0xFFFF8900),      // Not great
            Color(0xFFFFD54F),      // Okay
            Color(0xFFAED581),      // Good
            Color(0xFF2EC400)       // Great
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
            Column {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = Color.Black,
                    modifier = Modifier.clickable { navController.navigate(Routes.Home.route) }
                )
                VerticalSpacer(space = 16.dp)
                HeadingText(text = "Log your mood")
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val imageToShow = if (!sliderTouched) {
                    R.drawable.face0
                } else {
                    moodImages[sliderValue.toInt() - 1]
                }
                val textToShow = if (!sliderTouched) {
                    "How are you feeling?"
                } else {
                    moodDescriptions[sliderValue.toInt() - 1]
                }
                val textColor = if (!sliderTouched) {
                    Color.Black
                } else {
                    moodColors[sliderValue.toInt() - 1]
                }

                Image(
                    painter = painterResource(id = imageToShow),
                    contentDescription = "Mood Image",
                    modifier = Modifier.size(250.dp)
                )
                VerticalSpacer(space = 16.dp)
                SubheadingText(text = textToShow, color = textColor)
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(0.8f),
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
                    onValueChange = { value ->
                        sliderValue = value
                        sliderTouched = true
                    },
                    onValueChangeFinished = { sliderTouched = true },
                    valueRange = 1f..5f,
                    steps = 3,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    colors = SliderDefaults.colors(
                        activeTrackColor = ThemePurple2,
                        thumbColor = ThemePurple3
                    )
                )

                Button(
                    onClick = {
                        journalId?.let { jid ->
                            val newMoodLog = MoodLog(
                                score = sliderValue.toInt(),
                                journalId = jid
                            )
                            moodLogViewModel.createMoodLog(newMoodLog)
                        }
                        navController.navigate(Routes.Home.route)
                    },
                    enabled = sliderTouched,
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
}

@Preview(showBackground = true)
@Composable
fun PreviewMoodScreen() {
    val mockNavController = rememberNavController()
    MoodScreen(navController = mockNavController)
}
