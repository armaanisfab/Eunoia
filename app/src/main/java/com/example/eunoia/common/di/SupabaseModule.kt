package com.example.eunoia.common.di

import android.content.Context
import com.example.eunoia.common.utils.PropHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(@ApplicationContext context: Context): SupabaseClient {
        val (supabaseUrl, supabaseApiKey) = PropHelper.getSupabaseKeys(context)
        return createSupabaseClient(supabaseUrl = supabaseUrl, supabaseKey = supabaseApiKey)
        {
                this.install(Postgrest)
                this.install(Auth)
        }
    }
}
