package com.example.eunoia.feature.journal.data.remote

import com.example.eunoia.feature.journal.data.model.Journal
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class JournalService @Inject constructor(
    private val supabaseClient: SupabaseClient
) {
    suspend fun fetchUserJournal(userId: UUID): Journal? = withContext(Dispatchers.IO) {
        supabaseClient.from("journal")
            .select {
                filter {
                    Journal::userId eq userId
                }
            }
            .decodeList<Journal>()
            .firstOrNull()
    }

    suspend fun createJournal(journal: Journal): Journal? = withContext(Dispatchers.IO) {
        supabaseClient.from("journal")
            .insert(journal) {
                select()
            }
            .decodeSingleOrNull<Journal>()
    }

    suspend fun updateJournal(journal: Journal): Journal? = withContext(Dispatchers.IO) {
        supabaseClient.from("journal")
            .update(journal) {
                filter {
                    Journal::id eq journal.id
                }
            }
            .decodeSingleOrNull()
    }
}
