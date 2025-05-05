package com.example.eunoia.feature.profile.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val id: String,
    val username: String,
    val email: String,
    val createdAt: String? = null
)
