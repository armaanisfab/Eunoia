package com.example.eunoia.feature.journal.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.eunoia.feature.journal.data.model.JournalEntry

@Composable
fun JournalEntryList(entries: List<JournalEntry>) {
    println("Entries: $entries")
    Column {
        entries.forEach { entry ->
            JournalEntryCard(entry)
        }
    }
}
