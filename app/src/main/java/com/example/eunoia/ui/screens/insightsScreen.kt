package com.example.eunoia.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.eunoia.feature.feedback.presentation.ui.MarkdownPreview
import com.example.eunoia.feature.feedback.presentation.viewmodel.FeedbackViewModel
import com.example.eunoia.feature.journal.presentation.viewmodel.JournalViewModel
import com.example.eunoia.feature.profile.presentation.viewmodel.ProfileViewModel
import com.example.eunoia.ui.components.GradientSubheadingText
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.theme.ThemePurple3
import com.example.eunoia.ui.theme.space1
import com.example.eunoia.ui.theme.space2

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InsightsScreen(
    navController: NavController,
    journalViewModel: JournalViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    feedbackViewModel: FeedbackViewModel = hiltViewModel()
) {
    val profile by profileViewModel.profileState.collectAsState()
    val userId = profile?.id
    var kaboom by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = userId) {
        if (userId != null) {
            journalViewModel.fetchOrCreateUserJournal(userId)
        }
    }

    val isJournalLoading by journalViewModel.isLoading.collectAsState()
    val journalId = journalViewModel.journalState.collectAsState().value?.id
    val journalEntries by journalViewModel.entriesState.collectAsState()

    if (isJournalLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    val entryIds = journalEntries.map { it.id }
    feedbackViewModel.readAllFeedback(entryIds)
    val allFeedback by feedbackViewModel.feedbackState.collectAsState()

    println("All Feedback: $allFeedback")


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Sticky header: Top section with app background color
        stickyHeader {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background) // Use app's background color
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .clickable { navController.navigate(Routes.Home.route) }
                )
                VerticalSpacer(space = space2.dp)
                HeadingText(text = "Explore personalized AI insights")
                VerticalSpacer(space = space1.dp)
            }
        }

        // Scrollable content: Middle section
        items(allFeedback) { feedback ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = space2.dp),
                verticalArrangement = Arrangement.Center
            ) {
                GradientSubheadingText(text = feedback.title)
                VerticalSpacer(space = space2.dp)
//                NormalText(text = feedback.content)
                MarkdownPreview(feedback.content)
            }
        }

        // Bottom section: Submit button
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                VerticalSpacer(space = space1.dp)
                Button(
                    onClick = { navController.navigate(Routes.Home.route) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ThemePurple3,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Done")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInsightsScreen() {
    val mockNavController = rememberNavController()
    InsightsScreen(navController = mockNavController)
}