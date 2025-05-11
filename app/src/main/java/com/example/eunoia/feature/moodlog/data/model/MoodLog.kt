package com.example.eunoia.feature.moodlog.data.model

import com.example.eunoia.common.data.BaseModel
import com.example.eunoia.common.utils.serializers.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class MoodLog(
    @Serializable(with = UUIDSerializer::class)
    @SerialName("journal_id") val journalId: UUID,
    val score: Int,
) : BaseModel()
