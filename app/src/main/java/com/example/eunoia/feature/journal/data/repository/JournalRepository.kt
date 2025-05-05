package com.example.eunoia.feature.journal.data.repository

import com.example.eunoia.feature.journal.data.model.Journal
import com.example.eunoia.feature.journal.data.remote.JournalService
import javax.inject.Inject

class JournalRepository @Inject constructor(
    private val journalService: JournalService
) {
    suspend fun fetchUserJournal(userId: String): Journal? =
        journalService.fetchUserJournal(userId)

    suspend fun createJournal(journal: Journal): Journal? =
        journalService.createJournal(journal)

    suspend fun updateJournal(journal: Journal): Journal? =
        journalService.updateJournal(journal)
}
