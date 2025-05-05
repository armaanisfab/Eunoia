package com.example.eunoia.feature.auth.data.remote

import com.example.eunoia.feature.auth.data.model.AuthSession
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
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
            session?.let { AuthSession(it.accessToken, it.refreshToken) }
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
            session?.let { AuthSession(it.accessToken, it.refreshToken) }
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
}
