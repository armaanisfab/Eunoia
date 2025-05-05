package com.example.eunoia.feature.moodlog.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Streak(
    val id: String,
    val moodLogId: String,
    val streakDuration: Int,
    val streakCount: Int
)
