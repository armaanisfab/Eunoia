package com.example.eunoia.data.model

import java.time.Instant

data class User(
    val id: String,
    val email: String,
    val displayName: String,
    val createdAt: Instant
)
