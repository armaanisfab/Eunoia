package com.example.eunoia.feature.journal.data.model

import com.example.eunoia.common.data.BaseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JournalEntry(
    @SerialName("journal_id") val journalId: String,
    val content: String
): BaseModel()
