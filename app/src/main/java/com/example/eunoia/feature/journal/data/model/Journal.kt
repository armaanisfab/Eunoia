package com.example.eunoia.feature.journal.data.model

import com.example.eunoia.common.data.BaseModel
import com.example.eunoia.common.utils.serializers.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Journal(
    @Serializable(with = UUIDSerializer::class)
    @SerialName("user_id") val userId: UUID,
    val title: String
) : BaseModel()
