package com.example.eunoia.feature.profile.data.remote

import com.example.eunoia.feature.profile.data.model.Profile
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileService @Inject constructor(
    private val supabaseClient: SupabaseClient
) {
    suspend fun fetchProfile(userId: String): Profile? = withContext(Dispatchers.IO) {
        supabaseClient.from("profiles")
            .select {
                filter {
                    Profile::id eq userId
                }
            }
            .decodeList<Profile>()
            .firstOrNull()
    }

    suspend fun createProfile(profile: Profile): Profile? = withContext(Dispatchers.IO) {
        try {
            supabaseClient.from("profiles")
                .insert(profile) {
                    select()
                }
                .decodeSingleOrNull<Profile>()
        } catch (e: Exception) {
            println("Error creating profile: ${e.message}")
            null
        }
    }
}