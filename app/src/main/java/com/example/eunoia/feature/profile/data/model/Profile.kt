package com.example.eunoia.feature.profile.data.model

import com.example.eunoia.common.data.BaseModel
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val username: String,
    val email: String,
) : BaseModel()
