package com.example.eunoia.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.SubheadingText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.components.number_text_number_text
import com.example.eunoia.ui.components.heading_subheading
import com.example.eunoia.ui.theme.space1
import com.example.eunoia.ui.theme.space2
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.ChartEntry
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf

@Composable
fun ProgressScreen() {
    val chartEntryModelProducer = ChartEntryModelProducer(
        // sample data
        listOf(
            listOf(
                entryOf(0f, 3f),
                entryOf(1f, 4f),
                entryOf(2f, 2f),
                entryOf(3f, 5f)
            )
        )
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
            number_text_number_text(
                number1 = 5,
                text1 = "day streak",
                number2 = 9,
                text2 = "day high score",
                onClick = {
                    println("Card clicked!")
                }
            )
            VerticalSpacer(space = space1.dp)
            SubheadingText(text = "Your mood trend")
            VerticalSpacer(space = space2.dp)
            Chart(
                chart = lineChart(),
                chartModelProducer = chartEntryModelProducer,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                startAxis = rememberStartAxis(
                    title = "Mood Score"
                ),
                bottomAxis = rememberBottomAxis(
                    title = "Day"
                )
            )
            VerticalSpacer(space = space1.dp)
            SubheadingText(text = "Your history")
        }
        items(journalIDs) { journal ->
            VerticalSpacer(space = space2.dp)
            heading_subheading(
                heading = journal.toString(),
                subheading = "Journal entry and corresponding AI insight"
            )
        }
    }
}

private val journalIDs = arrayOf(12345, 23456, 34567, 45678)

@Preview(showBackground = true)
@Composable
fun PreviewProgressScreen() {
    ProgressScreen()
}