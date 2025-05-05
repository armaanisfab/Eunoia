package com.example.eunoia.feature.journal.data.remote

import com.example.eunoia.feature.journal.data.model.Journal
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JournalService @Inject constructor(
    private val supabaseClient: SupabaseClient
) {
    suspend fun fetchUserJournal(userId: String): Journal? = withContext(Dispatchers.IO) {
        // todo: why the hell did i capitalize my table names? why are they singular? lmao
        supabaseClient.from("Journal")
            .select(){
                filter {
                    Journal::userId eq userId
                }
            }
            .decodeList<Journal>()
            .firstOrNull()
    }

    suspend fun createJournal(journal: Journal): Journal? = withContext(Dispatchers.IO) {
        supabaseClient.from("Journal")
            .insert(journal)
            .decodeSingleOrNull()
    }

    suspend fun updateJournal(journal: Journal): Journal? = withContext(Dispatchers.IO) {
        supabaseClient.from("Journal")
            .update(journal) {
                filter {
                    Journal::id eq journal.id
                }
            }
            .decodeSingleOrNull()
    }
}
