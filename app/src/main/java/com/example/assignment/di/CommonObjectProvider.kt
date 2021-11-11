package com.example.assignment.di

import com.example.assignment.common.AppConstant.APP_DATE_FORMAT
import com.example.assignment.common.AppConstant.APP_SERVER_DATE_FORMAT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonObjectProvider {

    @Singleton
    @Provides
    @Named(value = APP_DATE_FORMAT)
    fun providesAppCalendarTimeFormat() =
        SimpleDateFormat(APP_DATE_FORMAT, Locale.getDefault()).apply {
            timeZone = TimeZone.getDefault()
        }

    @Singleton
    @Provides
    @Named(value = APP_SERVER_DATE_FORMAT)
    fun providesServerDateAlarmFormat() =
        SimpleDateFormat(APP_SERVER_DATE_FORMAT, Locale.getDefault()).apply {
            timeZone = TimeZone.getDefault()
        }
}