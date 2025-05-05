package com.example.eunoia.feature.moodlog.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MoodLog(
    val id: String,
    val journalId: String,
    val moodScore: Int,
    val logDate: String
)
