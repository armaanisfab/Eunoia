package com.example.eunoia.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.example.eunoia.feature.feedback.presentation.viewmodel.FeedbackViewModel
import com.example.eunoia.feature.journal.presentation.viewmodel.JournalViewModel
import com.example.eunoia.feature.profile.presentation.ui.MissingIdAlert
import com.example.eunoia.feature.profile.presentation.viewmodel.ProfileViewModel
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.theme.ThemePurple1
import com.example.eunoia.ui.theme.ThemePurple2
import com.example.eunoia.ui.theme.ThemePurple3
import com.example.eunoia.ui.theme.space2
import kotlinx.coroutines.delay

@Composable
fun JournalScreen(
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
        } else {
            delay(5000L)
            kaboom = true
        }
    }

    if (kaboom) {
        MissingIdAlert(
            onDismiss = { navController.navigate(Routes.Me.route) },
            onConfirm = { navController.navigate(Routes.Me.route) }
        )
        return
    }

    LaunchedEffect(Unit) {
        journalViewModel.newEntryEvent.collect { entry ->
            feedbackViewModel.submitJournalEntry(entry)
        }
    }

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

        // Middle section: Text field
        var textFieldValue by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            androidx.compose.material3.TextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                placeholder = { Text("Start typing your journal entry...") },
                modifier = Modifier
                    .size(height = 600.dp, width = 350.dp),
                colors = androidx.compose.material3.TextFieldDefaults.colors(
                    unfocusedContainerColor = ThemePurple1, // Color when no input or not focused
                    focusedContainerColor = ThemePurple2,  // Color when focused or active
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black
                )
            )
        }

        // Bottom section: Submit button
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
//                onClick = { navController.navigate(Routes.Home.route) },
                onClick = {
                    if (textFieldValue.isNotBlank()) {
                        journalViewModel.createJournalEntry(
                            textFieldValue
                        )
                        textFieldValue = ""
//                        feedbackViewModel.submitJournalEntry(
//                            journalViewModel.entriesState.value.last()
//                        )
                    }
                },
                enabled = textFieldValue.isNotEmpty(), // Button becomes active when text is entered
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (textFieldValue.isNotEmpty()) ThemePurple3 else Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Submit journal entry")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewJournalScreen() {
    val mockNavController = rememberNavController()
    JournalScreen(navController = mockNavController)
}
