package com.example.eunoia.feature.moodlog.data.repository

import com.example.eunoia.feature.moodlog.data.model.MoodLog
import com.example.eunoia.feature.moodlog.data.remote.MoodLogService
import javax.inject.Inject

class MoodLogRepository @Inject constructor(
    private val moodLogService: MoodLogService
) {
    suspend fun fetchMoodLogs(journalId: String): List<MoodLog> =
        moodLogService.fetchMoodLogs(journalId)

    suspend fun createMoodLog(moodLog: MoodLog): MoodLog? =
        moodLogService.createMoodLog(moodLog)

    suspend fun updateMoodLog(moodLog: MoodLog): MoodLog? =
        moodLogService.updateMoodLog(moodLog)
}
