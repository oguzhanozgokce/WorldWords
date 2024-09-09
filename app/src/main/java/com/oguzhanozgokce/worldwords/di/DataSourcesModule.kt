package com.oguzhanozgokce.worldwords.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.oguzhanozgokce.worldwords.data.SharedPreferencesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourcesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("learned_words_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesDataSource(
        sharedPreferences: SharedPreferences,
        gson: Gson
    ): SharedPreferencesDataSource {
        return SharedPreferencesDataSource(sharedPreferences, gson)
    }
}