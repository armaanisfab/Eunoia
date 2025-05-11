package com.example.eunoia.feature.moodlog.data.remote

import com.example.eunoia.feature.moodlog.data.model.Streak
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StreakService @Inject constructor(
    private val supabaseClient: SupabaseClient
) {
    suspend fun fetchStreak(moodLogId: String): Streak? = withContext(Dispatchers.IO) {
        supabaseClient.from("streak")
            .select {
                filter {
                    Streak::moodId eq moodLogId
                }
            }
            .decodeList<Streak>()
            .firstOrNull()
    }

    suspend fun createStreak(streak: Streak): Streak? = withContext(Dispatchers.IO) {
        supabaseClient.from("streak")
            .insert(streak) {
                select()
            }
            .decodeSingleOrNull()
    }

    suspend fun updateStreak(streak: Streak): Streak? = withContext(Dispatchers.IO) {
        supabaseClient.from("streak")
            .update(streak) {
                filter {
                    Streak::id eq streak.id
                }
            }
            .decodeSingleOrNull()
    }
}
