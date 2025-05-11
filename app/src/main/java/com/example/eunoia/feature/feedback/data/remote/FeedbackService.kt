package com.example.eunoia.feature.feedback.data.remote

import com.example.eunoia.common.client.LLMClient
import com.example.eunoia.feature.feedback.data.model.Feedback
import com.example.eunoia.feature.journal.data.model.JournalEntry
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import java.util.UUID
import javax.inject.Inject

class FeedbackService @Inject constructor(
    private val supabaseClient: SupabaseClient,
    private val llmClient: LLMClient
) {
    suspend fun requestFeedback(journalEntry: JournalEntry): Boolean {
        return llmClient.submitEntry(journalEntry).success
    }

    suspend fun readFeedback(entryId: UUID): Feedback? {
        return supabaseClient.from("feedback")
            .select {
                filter {
                    Feedback::entryId eq entryId
                }
            }
            .decodeSingleOrNull<Feedback>()
    }
}