package com.example.eunoia.feature.moodlog.data.model

import com.example.eunoia.common.data.BaseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoodLog(
    @SerialName("journal_id") val journalId: String,
    val score: Int,
) : BaseModel()
