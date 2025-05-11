package com.example.eunoia.feature.moodlog.data.model

import com.example.eunoia.common.data.BaseModel
import com.example.eunoia.common.utils.serializers.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Streak(
    @Serializable(with = UUIDSerializer::class)
    @SerialName("mood_id") val moodId: UUID,
    val count: Int
) : BaseModel()
