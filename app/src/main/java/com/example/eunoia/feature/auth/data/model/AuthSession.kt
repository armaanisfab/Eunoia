package com.example.eunoia.feature.auth.data.model

import java.util.UUID

data class AuthSession(
    val userId: UUID,
    val accessToken: String,
    val refreshToken: String
)
