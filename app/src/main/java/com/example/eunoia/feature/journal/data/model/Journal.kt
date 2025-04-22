package com.example.eunoia.feature.journal.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Journal(
    val id: String,
    val userId: String,
    val title: String
)
