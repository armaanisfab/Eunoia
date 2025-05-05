package com.example.eunoia.feature.auth.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eunoia.feature.auth.data.model.AuthSession
import com.example.eunoia.feature.auth.data.model.AuthState
import com.example.eunoia.feature.auth.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _userState = mutableStateOf<AuthState>(AuthState.Loading)
    val userState: State<AuthState> = _userState

    init {
        checkExistingSession()
    }

    private fun checkExistingSession() {
        viewModelScope.launch {
            val session: AuthSession? = authRepository.getSession()
            _userState.value = if (session != null) {
                AuthState.Authenticated(session)
            } else {
                AuthState.Unauthenticated
            }
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _userState.value = AuthState.Loading
            try {
                val session: AuthSession? = authRepository.signUp(email, password)
                _userState.value = if (session != null) {
                    AuthState.Authenticated(session)
                } else {
                    AuthState.Error("Sign up failed. Please try again.")
                }
            } catch (e: Exception) {
                _userState.value = AuthState.Error(e.message ?: "An unexpected error occurred.")
            }
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _userState.value = AuthState.Loading
            try {
                val session: AuthSession? = authRepository.signIn(email, password)
                _userState.value = if (session != null) {
                    AuthState.Authenticated(session)
                } else {
                    AuthState.Error("Sign in failed. Please try again.")
                }
            } catch (e: Exception) {
                _userState.value = AuthState.Error(e.message ?: "An unexpected error occurred.")
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            _userState.value = AuthState.Loading
            try {
                val success = authRepository.signOut()
                _userState.value = if (success) {
                    AuthState.Unauthenticated
                } else {
                    AuthState.Error("Sign out failed. Please try again.")
                }
            } catch (e: Exception) {
                _userState.value = AuthState.Error(e.message ?: "An unexpected error occurred.")
            }
        }
    }
}
