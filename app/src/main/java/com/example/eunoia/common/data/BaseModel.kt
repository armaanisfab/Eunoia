package com.example.eunoia.common.data

import com.example.eunoia.common.utils.serializers.InstantAsStringSerializer
import com.example.eunoia.common.utils.serializers.UUIDSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
open class BaseModel(
    @Serializable(with = UUIDSerializer::class)
    var id: UUID = UUID(0, 0),
    @SerialName("created_at")
    @Serializable(with = InstantAsStringSerializer::class)
    var createdAt: Instant = Instant.DISTANT_PAST
)