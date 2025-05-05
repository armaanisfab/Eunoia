package com.example.eunoia.feature.journal.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JournalEntry(
    val id: String,
    @SerialName("journal_id") val journalId: String,
    val content: String
)
