package com.example.eunoia.feature.auth.data.remote

import com.example.eunoia.feature.auth.data.model.AuthSession
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.user.UserInfo
import javax.inject.Inject

class AuthService @Inject constructor(
    private val supabaseClient: SupabaseClient
) {
    private val authClient = supabaseClient.auth

    suspend fun signUp(email: String, password: String): AuthSession? {
        return try {
            authClient.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            val session = authClient.currentSessionOrNull()
            session?.let { it.user?.let { it1 -> AuthSession(it1.id, it.accessToken, it.refreshToken) } }
        } catch (e: Exception) {
            println("Error signing up: ${e.message}")
            null
        }
    }

    suspend fun signIn(email: String, password: String): AuthSession? {
        return try {
            authClient.signInWith(Email) {
                this.email = email
                this.password = password
            }
            val session = authClient.currentSessionOrNull()
            session?.let { it.user?.let { it1 -> AuthSession(it1.id, it.accessToken, it.refreshToken) } }
        } catch (e: Exception) {
            println("Error signing in: ${e.message}")
            null
        }
    }

    suspend fun signOut(): Boolean {
        return try {
            authClient.signOut()
            true
        } catch (e: Exception) {
            println("Error signing out: ${e.message}")
            false
        }
    }

    suspend fun getUserDetails(userId: String): UserInfo? {
        return try {
            return authClient.retrieveUserForCurrentSession(updateSession = true)
        } catch (e: Exception) {
            println("Error fetching user details: ${e.message}")
            null
        }
    }
}
