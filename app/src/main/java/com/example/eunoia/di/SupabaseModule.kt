package com.example.eunoia.di

import android.content.Context
import com.example.eunoia.data.remote.SupabaseClient
import com.example.eunoia.common.utils.PropHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(@ApplicationContext context: Context): SupabaseClient {
        val (supabaseUrl, supabaseApiKey) = PropHelper.getSupabaseKeys(context)
        return SupabaseClient(
            baseUrl = supabaseUrl,
            apiKey = supabaseApiKey
        )
    }
}
