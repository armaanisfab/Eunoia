package com.example.eunoia.feature.journal.data.repository

import com.example.eunoia.feature.journal.data.model.JournalEntry
import com.example.eunoia.feature.journal.data.remote.JournalEntryService
import java.util.UUID
import javax.inject.Inject

class JournalEntryRepository @Inject constructor(
    private val journalEntryService: JournalEntryService
) {
    suspend fun fetchJournalEntries(journalId: String): List<JournalEntry> =
        journalEntryService.fetchJournalEntries(journalId)

//    suspend fun createJournalEntry(entry: JournalEntry): JournalEntry? =
//        journalEntryService.createJournalEntry(entry)

    suspend fun createJournalEntry(content: String, journalId: String): JournalEntry? =
        journalEntryService.createJournalEntry(
            JournalEntry(
                id = UUID.randomUUID().toString(),
                journalId = journalId,
                content = content
            )
        )

    suspend fun updateJournalEntry(entry: JournalEntry): JournalEntry? =
        journalEntryService.updateJournalEntry(entry)
}
