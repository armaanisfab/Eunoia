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
    suspend fun fetchJournals(): List<Journal> = withContext(Dispatchers.IO) {
        supabaseClient.from("Journal")
            .select()
            .decodeList<Journal>()
    }
}