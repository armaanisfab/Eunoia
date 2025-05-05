package com.example.eunoia.feature.auth.data.model

data class AuthSession(
    val userId: String,
    val accessToken: String,
    val refreshToken: String
)
