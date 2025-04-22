package com.example.eunoia.feature.entry.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Entry(
    val id: String,
    val journalId: String,
    val entryDate: String,
    val content: String
)
