package com.example.eunoia.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.eunoia.common.utils.ProgressStatsHelper.getHighestStreak
import com.example.eunoia.common.utils.ProgressStatsHelper.getLatestStreak
import com.example.eunoia.common.utils.ProgressStatsHelper.toChartEntries
import com.example.eunoia.feature.journal.presentation.ui.JournalEntryList
import com.example.eunoia.feature.journal.presentation.viewmodel.JournalViewModel
import com.example.eunoia.feature.moodlog.presentation.viewmodel.MoodLogViewModel
import com.example.eunoia.feature.profile.presentation.viewmodel.ProfileViewModel
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.SubheadingText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.components.number_text_number_text
import com.example.eunoia.ui.theme.space1
import com.example.eunoia.ui.theme.space2
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer

@Composable
fun ProgressScreen(
    navController: NavController,
    journalViewModel: JournalViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
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
    val journalEntries by journalViewModel.entriesState.collectAsState()

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
                moodLogViewModel.fetchStreaks(journalId)
                moodLogViewModel.fetchMoodLogs(journalId)
            }
        }

        val streaks by moodLogViewModel.streakState.collectAsState()
        val moodLogs by moodLogViewModel.moodLogsState.collectAsState()
        println("Collected ${streaks.size} streaks: $streaks")
        println("Collected ${moodLogs.size} mood logs: $moodLogs")

        val chartEntryModelProducer = ChartEntryModelProducer(
            (toChartEntries(moodLogs))
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
        ) {
            item {
                VerticalSpacer(space = space1.dp)
                HeadingText(text = "You're doing great!")
                VerticalSpacer(space = space1.dp)
            }
            item {
                number_text_number_text(
                    number1 = getLatestStreak(streaks)?.count ?: 0,
                    text1 = "day streak",
                    number2 = getHighestStreak(streaks)?.count ?: 0,
                    text2 = "day high score",
                    onClick = { println("Card clicked!") }
                )
                VerticalSpacer(space = space1.dp)
            }
            item {
                SubheadingText(text = "Your mood trend")
                VerticalSpacer(space = space2.dp)
            }
            item {
                Chart(
                    chart = lineChart(),
                    chartModelProducer = chartEntryModelProducer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    startAxis = rememberStartAxis(title = "Mood Score"),
                    bottomAxis = rememberBottomAxis(title = "Day")
                )
                VerticalSpacer(space = space1.dp)
            }
            item {
                SubheadingText(text = "Your history")
                VerticalSpacer(space = space1.dp)
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    JournalEntryList(entries = journalEntries)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProgressScreen() {
    val mockNavController = rememberNavController()
    ProgressScreen(mockNavController)
}