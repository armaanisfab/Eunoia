package com.example.eunoia.data.remote

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.eunoia.data.model.User
import java.time.Instant

class SupabaseClient(
    private val baseUrl: String,
    private val apiKey: String
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getUsers(): List<User> {
        return listOf(
            User("1", "alice@example.com", "Alice", Instant.now()),
            User("2", "bob@example.com", "Bob", Instant.now())
        )
    }
}
