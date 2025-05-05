package com.example.eunoia.feature.profile.data.model

data class AuthUser(
    val userId: String,
    val email: String,
    val username: String? = null
)
