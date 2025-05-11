package com.example.eunoia.feature.journal.data.remote

import com.example.eunoia.feature.journal.data.model.JournalEntry
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class JournalEntryService @Inject constructor(
    private val supabaseClient: SupabaseClient
) {
    suspend fun fetchJournalEntries(journalId: UUID): List<JournalEntry> =
        withContext(Dispatchers.IO) {
            supabaseClient.from("entry")
                .select {
                    filter {
                        JournalEntry::journalId eq journalId
                    }
                }
                .decodeList<JournalEntry>()
        }

    suspend fun createJournalEntry(entry: JournalEntry): JournalEntry? =
        withContext(Dispatchers.IO) {
            supabaseClient.from("entry")
                .insert(entry) {
                    select()
                }
                .decodeSingleOrNull<JournalEntry>()
        }

    suspend fun updateJournalEntry(entry: JournalEntry): JournalEntry? =
        withContext(Dispatchers.IO) {
            supabaseClient.from("entry")
                .update(entry) {
                    filter {
                        JournalEntry::id eq entry.id
                    }
                }
                .decodeSingleOrNull()
        }
}





