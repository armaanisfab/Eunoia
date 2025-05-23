package com.example.eunoia.feature.journal.data.repository

import com.example.eunoia.feature.journal.data.model.Journal
import com.example.eunoia.feature.journal.data.remote.JournalService
import java.util.UUID
import javax.inject.Inject

class JournalRepository @Inject constructor(
    private val journalService: JournalService
) {
    private suspend fun fetchUserJournal(userId: UUID): Journal? =
        journalService.fetchUserJournal(userId)

    suspend fun getOrCreateJournal(userId: UUID): Journal? {
        return fetchUserJournal(userId) ?: createJournal(
            Journal(
                userId = userId,
                title = "My Journal"
            )
        )
    }

    private suspend fun createJournal(journal: Journal): Journal? =
        journalService.createJournal(journal)

    suspend fun updateJournal(journal: Journal): Journal? =
        journalService.updateJournal(journal)
}
