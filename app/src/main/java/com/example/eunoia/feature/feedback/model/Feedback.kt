package com.example.eunoia.feature.feedback.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Feedback(
    val id: String,
    val entryId: String,
    val content: String
)
