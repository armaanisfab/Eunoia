package com.example.eunoia.common.utils

import android.content.Context
import java.util.Properties

object PropHelper {

    private const val CONFIG_FILE = "config.properties"

    // todo: broken, cant open file - use default vals for now
    private fun loadProperties(context: Context): Properties {
        val properties = Properties()
        try {
            context.assets.open(CONFIG_FILE).use { inputStream ->
                properties.load(inputStream)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return properties
    }

    fun getSupabaseKeys(context: Context): Pair<String, String> {
        val properties = loadProperties(context)
        val url = properties.getProperty("SUPABASE_URL", "http://10.0.2.2:54321")
        val apiKey = properties.getProperty("SUPABASE_API_KEY", "")
        return Pair(url, apiKey)
    }
}
