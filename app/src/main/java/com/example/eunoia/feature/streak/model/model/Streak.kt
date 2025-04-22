package com.example.eunoia.feature.streak.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Streak(
    val id: String,
    val logId: String,
    val startDate: String,
    val count: Int 
)
