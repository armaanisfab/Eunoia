package com.example.eunoia.feature.feedback.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eunoia.feature.feedback.data.model.Feedback
import com.example.eunoia.feature.feedback.data.repository.FeedbackRepository
import com.example.eunoia.feature.journal.data.model.JournalEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(
    private val feedbackRepository: FeedbackRepository
) : ViewModel() {
    private val _submissionStatus = MutableStateFlow<Boolean?>(null)
    val submissionStatus: StateFlow<Boolean?> = _submissionStatus

    private val _feedbackState = MutableStateFlow<Feedback?>(null)
    val feedbackState: StateFlow<Feedback?> = _feedbackState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun submitJournalEntry(journalEntry: JournalEntry) {
        viewModelScope.launch {
            _isLoading.value = true
            _submissionStatus.value = feedbackRepository.requestFeedback(journalEntry)
            _isLoading.value = false
        }
    }

    fun refreshFeedback(entryId: UUID) {
        viewModelScope.launch {
            _isLoading.value = true
            _feedbackState.value = feedbackRepository.readFeedback(entryId)
            _submissionStatus.value = _feedbackState.value != null
            _isLoading.value = false
        }
    }

}