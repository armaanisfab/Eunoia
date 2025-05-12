package com.example.eunoia.feature.journal.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.eunoia.feature.journal.data.model.JournalEntry
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.theme.space2

@Composable
fun JournalEntryList(entries: List<JournalEntry>) {
    println("Entries: $entries")
    Column {
        entries.forEach { entry ->
            JournalEntryCard(entry)
            VerticalSpacer(space = space2.dp)
        }
    }
}
