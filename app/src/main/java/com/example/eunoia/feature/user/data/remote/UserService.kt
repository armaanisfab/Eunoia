package com.example.eunoia.feature.user.data.remote

import com.example.eunoia.feature.user.data.model.User
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserService @Inject constructor(
    private val supabaseClient: SupabaseClient
) {
    suspend fun fetchUsers(): List<User> = withContext(Dispatchers.IO) {
        supabaseClient.from("User")
            .select()
            .decodeList<User>()
    }
}
