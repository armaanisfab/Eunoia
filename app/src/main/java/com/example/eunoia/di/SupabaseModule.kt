package com.example.eunoia.di

import com.example.eunoia.data.remote.SupabaseClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        val baseUrl = "YOUR_SUPABASE_URL"
        val apiKey = "YOUR_SUPABASE_API_KEY"
        return SupabaseClient(baseUrl, apiKey)
    }
}
