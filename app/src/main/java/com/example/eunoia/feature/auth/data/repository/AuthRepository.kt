package com.example.eunoia.feature.auth.data.repository

import com.example.eunoia.feature.auth.data.local.SessionManager
import com.example.eunoia.feature.auth.data.model.AuthSession
import com.example.eunoia.feature.auth.data.remote.AuthService
import com.example.eunoia.feature.profile.data.model.AuthUser
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService,
    private val sessionManager: SessionManager
) {
    suspend fun signUp(email: String, password: String, username: String): AuthSession? {
        val session = authService.signUp(email, password, username)
        session?.let {
            sessionManager.saveSession(it.userId, it.accessToken, it.refreshToken)
        }
        return session
    }

    suspend fun signIn(email: String, password: String): AuthSession? {
        val session = authService.signIn(email, password)
        session?.let {
            sessionManager.saveSession(it.userId, it.accessToken, it.refreshToken)
        }
        return session
    }

    suspend fun signOut(): Boolean {
        val success = authService.signOut()
        if (success) {
            sessionManager.clearSession()
        }
        return success
    }

    fun getSession(): AuthSession? {
        return sessionManager.getSession()
    }

    suspend fun getUserDetails(userId: String): AuthUser? {
        val authUser = authService.getUserDetails(userId)
        return if (authUser?.id == userId) {
            AuthUser(
                userId = authUser.id,
                email = authUser.email ?: "unknown@example.com",
                username = authUser.userMetadata?.get("username")?.toString() ?: authUser.email?.substringBefore("@") ?: "unknown"
            )
        } else {
            null
        }
    }
}