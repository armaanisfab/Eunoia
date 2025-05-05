package com.example.eunoia.feature.journal.data.repository

import com.example.eunoia.feature.journal.data.model.JournalEntry
import com.example.eunoia.feature.journal.data.remote.JournalEntryService
import javax.inject.Inject

class JournalEntryRepository @Inject constructor(
    private val journalEntryService: JournalEntryService
) {
    suspend fun fetchJournalEntries(journalId: String): List<JournalEntry> =
        journalEntryService.fetchJournalEntries(journalId)

    suspend fun createJournalEntry(entry: JournalEntry): JournalEntry? =
        journalEntryService.createJournalEntry(entry)

    suspend fun updateJournalEntry(entry: JournalEntry): JournalEntry? =
        journalEntryService.updateJournalEntry(entry)
}
