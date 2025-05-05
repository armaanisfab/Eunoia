package com.example.eunoia.feature.auth.data.model

sealed class AuthState {
    data object Loading : AuthState()
    data class Authenticated(val session: AuthSession) : AuthState()
    data class Error(val message: String) : AuthState()
    data object Unauthenticated : AuthState()
}
