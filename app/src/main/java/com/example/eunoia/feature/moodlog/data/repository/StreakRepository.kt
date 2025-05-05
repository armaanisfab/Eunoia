package com.example.eunoia.feature.moodlog.data.repository

import com.example.eunoia.feature.moodlog.data.model.Streak
import com.example.eunoia.feature.moodlog.data.remote.StreakService
import javax.inject.Inject

class StreakRepository @Inject constructor(
    private val streakService: StreakService
) {
    suspend fun fetchStreak(moodLogId: String): Streak? =
        streakService.fetchStreak(moodLogId)

    suspend fun createStreak(streak: Streak): Streak? =
        streakService.createStreak(streak)

    suspend fun updateStreak(streak: Streak): Streak? =
        streakService.updateStreak(streak)
}
