package com.example.eunoia.feature.moodlog.data.repository

import com.example.eunoia.feature.moodlog.data.model.Streak
import com.example.eunoia.feature.moodlog.data.remote.StreakService
import java.util.UUID
import javax.inject.Inject

class StreakRepository @Inject constructor(
    private val streakService: StreakService
) {
    suspend fun fetchStreak(moodLogId: UUID): Streak? =
        streakService.fetchStreak(moodLogId)

    suspend fun fetchStreaks(moodLogIds: List<UUID>): List<Streak> {
        println("Fetching streaks for mood log IDs: $moodLogIds")
        return moodLogIds.mapNotNull { streakService.fetchStreak(it) }
    }

    suspend fun createStreak(streak: Streak): Streak? =
        streakService.createStreak(streak)

    suspend fun updateStreak(streak: Streak): Streak? =
        streakService.updateStreak(streak)
}
