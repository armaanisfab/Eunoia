package com.example.eunoia.feature.moodlog.data.model

import com.example.eunoia.common.data.BaseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Streak(
    @SerialName("mood_id") val moodId: String,
    val count: Int
) : BaseModel()
