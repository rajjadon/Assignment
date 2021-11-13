package com.example.assignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assignment.BuildConfig
import com.example.assignment.data.model.PersonLocal
import javax.inject.Singleton

@Singleton
@Database(entities = [PersonLocal::class], version = BuildConfig.VERSION_CODE, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val localDao: LocalDao
}