package com.oguzhanozgokce.worldwords.di

import com.oguzhanozgokce.worldwords.data.WordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWordRepository(): WordRepository {
        return WordRepository()
    }

}