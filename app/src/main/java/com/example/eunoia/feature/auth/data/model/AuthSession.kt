package com.example.eunoia.feature.auth.data.model

data class AuthSession(
    val accessToken: String,
    val refreshToken: String
)
