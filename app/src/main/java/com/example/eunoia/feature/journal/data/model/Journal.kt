package com.example.eunoia.feature.journal.data.model

import com.example.eunoia.common.data.BaseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Journal(
    @SerialName("user_id") val userId: String,
    val title: String
): BaseModel()
