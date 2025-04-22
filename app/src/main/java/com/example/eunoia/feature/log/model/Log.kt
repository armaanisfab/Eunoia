package com.example.eunoia.feature.log.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Log(
    val id: String,
    val journalId: String,
    val moodScore: Int,
    val highestStreak: Int 
)
