package com.example.assignment.di

import android.content.Context
import androidx.room.Room
import com.example.assignment.data.local.AppDatabase
import com.example.assignment.data.local.LocalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): LocalDao {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "APP_DATABASE.db"
        ).fallbackToDestructiveMigration().build().localDao
    }
}