package com.example.eunoia.feature.feedback.data.repository

import com.example.eunoia.feature.feedback.data.model.Feedback
import com.example.eunoia.feature.feedback.data.remote.FeedbackService
import com.example.eunoia.feature.journal.data.model.JournalEntry
import java.util.UUID
import javax.inject.Inject

class FeedbackRepository @Inject constructor(private val feedbackService: FeedbackService) {
    suspend fun requestFeedback(journalEntry: JournalEntry): Boolean =
        feedbackService.requestFeedback(journalEntry)

    suspend fun readFeedback(entryId: UUID): Feedback? =
        feedbackService.readFeedback(entryId)

    suspend fun readAllFeedback(entryIds: List<UUID>): List<Feedback> {
        return entryIds.mapNotNull { readFeedback(it) }
    }
}