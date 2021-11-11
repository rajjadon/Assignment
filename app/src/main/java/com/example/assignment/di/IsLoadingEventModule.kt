package com.example.assignment.di

import com.example.assignment.common.Utills.IsLoadingEvent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IsLoadingEventModule {

    @Singleton
    @Provides
    fun provideIsLoading() = IsLoadingEvent()

}