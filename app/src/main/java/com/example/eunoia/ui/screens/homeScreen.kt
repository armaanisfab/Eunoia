package com.example.eunoia.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.eunoia.R
import com.example.eunoia.common.utils.OffsetDTHelper.isToday
import com.example.eunoia.feature.journal.presentation.viewmodel.JournalViewModel
import com.example.eunoia.feature.moodlog.presentation.viewmodel.MoodLogViewModel
import com.example.eunoia.feature.profile.presentation.viewmodel.ProfileViewModel
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.SubheadingText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.components.icon_heading_subheading
import com.example.eunoia.ui.screens.components.ImageCarousel
import com.example.eunoia.ui.theme.space1
import com.example.eunoia.ui.theme.space2

@Composable
fun HomeScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    journalViewModel: JournalViewModel = hiltViewModel(),
    moodLogViewModel: MoodLogViewModel = hiltViewModel()
) {
    val profile by profileViewModel.profileState.collectAsState()
    val userId = profile?.id

    LaunchedEffect(key1 = userId) {
        userId?.let {
            journalViewModel.fetchOrCreateUserJournal(it)
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
        LaunchedEffect(key1 = journalId) {
            journalId?.let { moodLogViewModel.fetchMoodLogs(it) }
        }
    }

    val moodLogs by moodLogViewModel.moodLogsState.collectAsState()

    val canSubmitMoodLog = remember(moodLogs) {
        moodLogs.lastOrNull()?.let { !it.createdAt.isToday() } ?: true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {
        HeadingText(text = "Welcome to Eunoia")
        VerticalSpacer(space = space1.dp)

        val homeScreenImageCarousel = listOf(
            R.drawable.quote1,
            R.drawable.quote2,
            R.drawable.quote3,
            R.drawable.quote4
        )
        ImageCarousel(imageResources = homeScreenImageCarousel)
        VerticalSpacer(space = space1.dp)
        SubheadingText(text = "Check in with yourself")
        VerticalSpacer(space = space2.dp)

        if (canSubmitMoodLog) {
            icon_heading_subheading(
                heading = "Log your mood",
                subheading = "How are you feeling today?",
                iconResId = R.drawable.smile_icon,
                onClick = { navController.navigate(Routes.Mood.route) }
            )
            VerticalSpacer(space = space2.dp)
        }

        icon_heading_subheading(
            heading = "Pen your journal",
            subheading = "What's on your mind?",
            iconResId = R.drawable.pen_icon,
            onClick = { navController.navigate(Routes.Journal.route) }
        )
        VerticalSpacer(space = space2.dp)
        icon_heading_subheading(
            heading = "Explore personalized AI insights",
            subheading = "Advice from your wellness coach",
            iconResId = R.drawable.chat_icon,
            onClick = { navController.navigate(Routes.Insights.route) }
        )
        VerticalSpacer(space = space1.dp)
        SubheadingText(text = "Take a breather")
        VerticalSpacer(space = space2.dp)
        icon_heading_subheading(
            heading = "Meditate",
            subheading = "Start a relaxing meditation audio",
            iconResId = R.drawable.shield_icon,
            onClick = { navController.navigate(Routes.Meditation.route) }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val mockNavController = rememberNavController()
    HomeScreen(navController = mockNavController)
}
