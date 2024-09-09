package com.oguzhanozgokce.worldwords.di

import com.oguzhanozgokce.worldwords.data.SharedPreferencesDataSource
import com.oguzhanozgokce.worldwords.data.WordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWordRepository(
        sharedPreferencesDataSource: SharedPreferencesDataSource
    ): WordRepository {
        return WordRepository(sharedPreferencesDataSource)
    }
}