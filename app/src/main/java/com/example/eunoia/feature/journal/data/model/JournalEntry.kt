package com.example.eunoia.feature.journal.data.model

import kotlinx.serialization.Serializable

@Serializable
data class JournalEntry(
    val id: String,
    val journalId: String,
    val entryDate: String,
    val content: String
)
