package com.example.eunoia.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.eunoia.data.model.User
import com.example.eunoia.data.remote.SupabaseClient
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val supabaseClient: SupabaseClient
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun fetchUsers(): List<User> {
        return supabaseClient.getUsers()
    }
}
