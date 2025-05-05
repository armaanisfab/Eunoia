package com.example.eunoia.feature.moodlog.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eunoia.feature.moodlog.data.model.MoodLog
import com.example.eunoia.feature.moodlog.data.model.Streak
import com.example.eunoia.feature.moodlog.data.repository.MoodLogRepository
import com.example.eunoia.feature.moodlog.data.repository.StreakRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoodLogViewModel @Inject constructor(
    private val moodLogRepository: MoodLogRepository,
    private val streakRepository: StreakRepository
) : ViewModel() {

    private val _moodLogsState = MutableStateFlow<List<MoodLog>>(emptyList())
    val moodLogsState: StateFlow<List<MoodLog>> = _moodLogsState

    private val _streakState = MutableStateFlow<Streak?>(null)
    val streakState: StateFlow<Streak?> = _streakState

    fun fetchMoodLogs(journalId: String) {
        viewModelScope.launch {
            val logs = moodLogRepository.fetchMoodLogs(journalId)
            _moodLogsState.value = logs

            logs.lastOrNull()?.let { latestLog ->
                _streakState.value = streakRepository.fetchStreak(latestLog.id)
            }
        }
    }

    fun createMoodLog(moodLog: MoodLog) {
        viewModelScope.launch {
            moodLogRepository.createMoodLog(moodLog)?.let { newLog ->
                _moodLogsState.value += newLog
                streakRepository.createStreak(
                    Streak(
                        id = "",
                        moodLogId = newLog.id,
                        streakDuration = 1,
                        streakCount = 1
                    )
                )?.let { newStreak ->
                    _streakState.value = newStreak
                }
            }
        }
    }

    fun updateMoodLog(moodLog: MoodLog) {
        viewModelScope.launch {
            moodLogRepository.updateMoodLog(moodLog)?.let { updatedLog ->
                _moodLogsState.value = _moodLogsState.value.map {
                    if (it.id == updatedLog.id) updatedLog else it
                }
            }
        }
    }

    fun createOrUpdateStreak(streak: Streak) {
        viewModelScope.launch {
            val existing = streakRepository.fetchStreak(streak.moodLogId)
            val updatedStreak = if (existing == null) {
                streakRepository.createStreak(streak)
            } else {
                streakRepository.updateStreak(streak)
            }
            _streakState.value = updatedStreak
        }
    }

    fun updateStreak(streak: Streak) {
        viewModelScope.launch {
            streakRepository.updateStreak(streak)?.let { updated ->
                _streakState.value = updated
            }
        }
    }
}
