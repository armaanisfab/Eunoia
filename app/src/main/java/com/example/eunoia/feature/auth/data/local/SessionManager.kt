package com.example.eunoia.feature.auth.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.eunoia.feature.auth.data.model.AuthSession
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.UUID
import javax.inject.Inject

class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val USER_ID = "user_id"
        private const val KEY_ACCESS_TOKEN = "key_access_token"
        private const val KEY_REFRESH_TOKEN = "key_refresh_token"
    }

    fun saveSession(userId: UUID, accessToken: String, refreshToken: String) {
        prefs.edit().apply {
            putString(USER_ID, userId.toString())
            putString(KEY_ACCESS_TOKEN, accessToken)
            putString(KEY_REFRESH_TOKEN, refreshToken)
            apply()
        }
    }

    fun getSession(): AuthSession? {
        val userId = prefs.getString(USER_ID, null)
        val accessToken = prefs.getString(KEY_ACCESS_TOKEN, null)
        val refreshToken = prefs.getString(KEY_REFRESH_TOKEN, null)
        return if (userId != null && accessToken != null && refreshToken != null) {
            AuthSession(UUID.fromString(userId), accessToken, refreshToken)
        } else null
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
