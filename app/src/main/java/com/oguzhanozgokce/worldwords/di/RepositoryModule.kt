package com.oguzhanozgokce.worldwords.di

import android.content.Context
import com.oguzhanozgokce.worldwords.data.SharedPreferencesDataSource
import com.oguzhanozgokce.worldwords.data.WordRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWordRepository(
        sharedPreferencesDataSource: SharedPreferencesDataSource, @ApplicationContext context: Context
    ): WordRepository {
        return WordRepository(sharedPreferencesDataSource, context)
    }
}