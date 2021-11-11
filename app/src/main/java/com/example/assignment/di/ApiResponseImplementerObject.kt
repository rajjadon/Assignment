package com.example.assignment.di

import com.example.assignment.common.utills.IsLoadingEvent
import com.example.assignment.data.remote.apiCallAndReciver.ApiCallsImplementer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [IsLoadingEventModule::class])
@InstallIn(SingletonComponent::class)
object ApiResponseImplementerObject {

    @Singleton
    @Provides
    fun providesApiResponseImplementerObject(isLoadingEvent: IsLoadingEvent) =
        ApiCallsImplementer(isLoadingEvent)
}