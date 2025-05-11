package com.example.eunoia.common.utils

import com.example.eunoia.feature.moodlog.data.model.MoodLog
import com.example.eunoia.feature.moodlog.data.model.Streak
import com.patrykandpatrick.vico.core.entry.ChartEntry
import com.patrykandpatrick.vico.core.entry.entryOf

object ProgressStatsHelper {
    fun toChartEntries(logs: List<MoodLog>): List<List<ChartEntry>> {
        val sortedLogs = logs.sortedBy { it.createdAt }
        val entries = sortedLogs.mapIndexed { index, log ->
            entryOf(index.toFloat(), log.score.toFloat())
        }
        println("Converted ${sortedLogs.size} logs to chart entries: $entries")
        return listOf(entries)
    }

    fun getHighestStreak(streaks: List<Streak>): Streak? {
        return streaks.maxByOrNull { it.count }
    }

    fun getLatestStreak(streaks: List<Streak>): Streak? {
        println("Sorted streaks by createdAt: ${streaks.sortedBy { it.createdAt }}")
        return streaks.maxByOrNull { it.createdAt }
    }
}