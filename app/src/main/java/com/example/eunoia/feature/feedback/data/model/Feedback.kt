package com.example.eunoia.feature.feedback.data.model

import com.example.eunoia.common.data.BaseModel
import com.example.eunoia.common.utils.serializers.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Feedback(
    @Serializable(with = UUIDSerializer::class)
    @SerialName("entry_id") val entryId: UUID,
    val content: String,
) : BaseModel()