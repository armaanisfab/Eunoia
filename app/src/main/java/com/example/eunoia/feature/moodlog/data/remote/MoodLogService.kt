package com.example.eunoia.feature.moodlog.data.remote

import com.example.eunoia.feature.moodlog.data.model.MoodLog
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoodLogService @Inject constructor(
    private val supabaseClient: SupabaseClient
) {
    suspend fun fetchMoodLogs(journalId: String): List<MoodLog> = withContext(Dispatchers.IO) {
        supabaseClient.from("MoodLog")
            .select() {
                filter {
                    MoodLog::journalId eq journalId
                }
            }
            .decodeList<MoodLog>()
    }

    suspend fun createMoodLog(moodLog: MoodLog): MoodLog? = withContext(Dispatchers.IO) {
        supabaseClient.from("MoodLog")
            .insert(moodLog)
            .decodeSingleOrNull()
    }

    suspend fun updateMoodLog(moodLog: MoodLog): MoodLog? = withContext(Dispatchers.IO) {
        supabaseClient.from("MoodLog")
            .update(moodLog){
                filter {
                    MoodLog::id eq moodLog.id
                }
            }
            .decodeSingleOrNull()
    }
}
