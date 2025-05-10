package com.example.eunoia.feature.auth.data.model

import com.example.eunoia.common.data.BaseModel

data class AuthUser(
    val userId: String,
    val email: String,
    val username: String? = null
) : BaseModel()
