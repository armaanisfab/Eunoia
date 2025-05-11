package com.example.eunoia.feature.moodlog.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eunoia.feature.moodlog.data.model.MoodLog
import com.example.eunoia.feature.moodlog.data.model.Streak
import com.example.eunoia.feature.moodlog.data.repository.MoodLogRepository
import com.example.eunoia.feature.moodlog.data.repository.StreakRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

    fun fetchMoodLogs(journalId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val logs = moodLogRepository.fetchMoodLogs(journalId)
            _moodLogsState.value = logs

            logs.lastOrNull()?.let { latestLog ->
                _streakState.value = streakRepository.fetchStreak(latestLog.id)
            }
            _isLoading.value = false
        }
    }

    fun createMoodLog(moodLog: MoodLog) {
        viewModelScope.launch {
            _isLoading.value = true
            moodLogRepository.createMoodLog(moodLog)?.let { newLog ->
                _moodLogsState.value += newLog
                streakRepository.createStreak(
                    Streak(
                        moodId = newLog.id,
                        count = 1,
                    )
                )?.let { newStreak ->
                    _streakState.value = newStreak
                }
            }
            _isLoading.value = false
        }
    }

    fun updateMoodLog(moodLog: MoodLog) {
        viewModelScope.launch {
            _isLoading.value = true
            moodLogRepository.updateMoodLog(moodLog)?.let { updatedLog ->
                _moodLogsState.value = _moodLogsState.value.map {
                    if (it.id == updatedLog.id) updatedLog else it
                }
            }
            _isLoading.value = false
        }
    }

    fun createOrUpdateStreak(streak: Streak) {
        viewModelScope.launch {
            _isLoading.value = true
            val existing = streakRepository.fetchStreak(streak.moodId)
            val updatedStreak = if (existing == null) {
                streakRepository.createStreak(streak)
            } else {
                streakRepository.updateStreak(streak)
            }
            _streakState.value = updatedStreak
            _isLoading.value = false
        }
    }

    fun updateStreak(streak: Streak) {
        viewModelScope.launch {
            _isLoading.value = true
            streakRepository.updateStreak(streak)?.let { updated ->
                _streakState.value = updated
            }
            _isLoading.value = false
        }
    }
}
