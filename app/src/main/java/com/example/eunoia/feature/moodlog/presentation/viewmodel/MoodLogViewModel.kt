package com.example.eunoia.feature.moodlog.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eunoia.common.utils.OffsetDTHelper.isToday
import com.example.eunoia.common.utils.OffsetDTHelper.isYesterday
import com.example.eunoia.feature.moodlog.data.model.MoodLog
import com.example.eunoia.feature.moodlog.data.model.Streak
import com.example.eunoia.feature.moodlog.data.repository.MoodLogRepository
import com.example.eunoia.feature.moodlog.data.repository.StreakRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MoodLogViewModel @Inject constructor(
    private val moodLogRepository: MoodLogRepository,
    private val streakRepository: StreakRepository
) : ViewModel() {

    private val _moodLogsState = MutableStateFlow<List<MoodLog>>(emptyList())
    val moodLogsState: StateFlow<List<MoodLog>> = _moodLogsState

    private val _streakState = MutableStateFlow<Streak?>(null)
    val streakState: StateFlow<Streak?> = _streakState

    private val _errorState = MutableStateFlow("")
    val errorState: StateFlow<String> = _errorState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            _moodLogsState.collect { moodLogs ->
                moodLogs.lastOrNull()?.let { latestLog ->
                    _streakState.value = streakRepository.fetchStreak(latestLog.id)
                }
            }
        }
    }

    fun fetchMoodLogs(journalId: UUID) {
        viewModelScope.launch {
            _isLoading.value = true
            val logs = moodLogRepository.fetchMoodLogs(journalId)
                .sortedBy { it.createdAt }
            _moodLogsState.value = logs
            _isLoading.value = false
        }
    }

    fun createMoodLog(newMoodLog: MoodLog) {
        viewModelScope.launch {
            _isLoading.value = true

            val currentLogs = _moodLogsState.value.sortedBy { it.createdAt }
            val lastLog = currentLogs.lastOrNull()

            if (lastLog != null && lastLog.createdAt.isToday()) {
                _errorState.value = "Mood log for today already exists"
                _isLoading.value = false
                return@launch
            }

            val createdLog = moodLogRepository.createMoodLog(newMoodLog)
            if (createdLog == null) {
                _errorState.value = "Failed to create mood log"
                _isLoading.value = false
                return@launch
            }

            _moodLogsState.value = currentLogs + createdLog

            if (lastLog != null && lastLog.createdAt.isYesterday()) {
                val currentStreak = streakRepository.fetchStreak(lastLog.id)
                println("Current streak: $currentStreak")
                val updatedStreak = if (currentStreak != null) {
                    println("Updating streak: $currentStreak")
                    streakRepository.updateStreak(currentStreak.copy(count = currentStreak.count + 1))
                } else {
                    println("Creating new streak (old one not found)")
                    streakRepository.createStreak(Streak(moodId = createdLog.id, count = 1))
                }
                println("Updated streak: $updatedStreak")
                _streakState.value = updatedStreak
            } else {
                println("Creating new streak (broke the streak)")
                val newStreak =
                    streakRepository.createStreak(Streak(moodId = createdLog.id, count = 1))
                _streakState.value = newStreak
            }
            _isLoading.value = false
        }
    }
}
