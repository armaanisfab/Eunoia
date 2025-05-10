package com.example.eunoia.feature.journal.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eunoia.feature.journal.data.model.JournalEntry

@Composable
fun JournalEntryCard(entry: JournalEntry) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = entry.createdAt, style = MaterialTheme.typography.titleMedium)
            Text(text = entry.content, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
