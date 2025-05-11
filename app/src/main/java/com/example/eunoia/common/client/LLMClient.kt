package com.example.eunoia.common.client

import com.example.eunoia.feature.feedback.data.model.JournalSubmissionResponse
import com.example.eunoia.feature.journal.data.model.JournalEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.io.IOException
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LLMClient @Inject constructor() {
    private val okHttpClient = OkHttpClient()

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun submitEntry(journalEntry: JournalEntry): JournalSubmissionResponse {
        val jsonBody = json.encodeToString(journalEntry)
        val requestBody = jsonBody.toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("http://10.0.2.2:8000/api/submit")
            .post(requestBody)
            .build()

        return withContext(Dispatchers.IO) {
            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    throw IOException("Unexpected code $response")
                }
                val responseString = response.body?.string()
                    ?: throw IOException("Empty response body")
                json.decodeFromString(responseString)
            }
        }
    }
}