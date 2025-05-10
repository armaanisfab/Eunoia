package com.example.eunoia.feature.journal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eunoia.feature.journal.data.model.Journal
import com.example.eunoia.feature.journal.data.model.JournalEntry
import com.example.eunoia.feature.journal.data.repository.JournalEntryRepository
import com.example.eunoia.feature.journal.data.repository.JournalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JournalViewModel @Inject constructor(
    private val journalRepository: JournalRepository,
    private val journalEntryRepository: JournalEntryRepository
) : ViewModel() {

    private val _journalState = MutableStateFlow<Journal?>(null)
    val journalState: StateFlow<Journal?> = _journalState

    private val _entriesState = MutableStateFlow<List<JournalEntry>>(emptyList())
    val entriesState: StateFlow<List<JournalEntry>> = _entriesState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            _journalState.collect { journal ->
                journal?.id?.let { journalId ->
                    fetchJournalEntries(journalId)
                }
            }
        }
    }

    fun fetchOrCreateUserJournal(userId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _journalState.value = journalRepository.getOrCreateJournal(userId)
            println("Fetched or created journal: ${_journalState.value?.id}")
            _isLoading.value = false
        }
    }

    fun createJournalEntry(content: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _journalState.value?.id?.let { journalId ->
                journalEntryRepository.createJournalEntry(content, journalId)
                println("Created journal entry: $journalId")
                fetchJournalEntries(journalId)
            }
            _isLoading.value = false
        }
    }

    private fun fetchJournalEntries(journalId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            println("Fetching journal entries for journal ID: $journalId")
            _entriesState.value = journalEntryRepository.fetchJournalEntries(journalId)
            _isLoading.value = false
        }
    }
}


