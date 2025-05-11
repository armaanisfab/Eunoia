package com.example.eunoia.feature.feedback.data.model

import kotlinx.serialization.Serializable

@Serializable
data class JournalSubmissionResponse(
    val success: Boolean,
    val message: String? = null
)