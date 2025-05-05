package com.example.eunoia.feature.journal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eunoia.feature.journal.data.model.Journal
import com.example.eunoia.feature.journal.data.model.JournalEntry
import com.example.eunoia.feature.journal.data.repository.JournalEntryRepository
import com.example.eunoia.feature.journal.data.repository.JournalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class JournalViewModel @Inject constructor(
    private val journalRepository: JournalRepository,
    private val journalEntryRepository: JournalEntryRepository
) : ViewModel() {

    private val _journalState = MutableStateFlow<Journal?>(null)
    val journalState: StateFlow<Journal?> = _journalState

    private val _entriesState = MutableStateFlow<List<JournalEntry>>(emptyList())
    val entriesState: StateFlow<List<JournalEntry>> = _entriesState

    fun fetchUserJournal(userId: String) {
        viewModelScope.launch {
            _journalState.value = journalRepository.fetchUserJournal(userId)
        }
    }

    fun fetchJournalEntries(journalId: String) {
        viewModelScope.launch {
            _entriesState.value = journalEntryRepository.fetchJournalEntries(journalId)
        }
    }

    fun createJournalEntry(entry: JournalEntry) {
        viewModelScope.launch {
            val newEntry = journalEntryRepository.createJournalEntry(entry)
            if (newEntry != null) {
                _entriesState.value += newEntry
            }
        }
    }
}
