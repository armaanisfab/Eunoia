package com.example.eunoia.feature.journal.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Journal(
    val id: String,
    @SerialName("user_id") val userId: String,
    val title: String
)