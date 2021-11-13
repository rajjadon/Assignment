package com.example.assignment.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import com.example.assignment.data.model.PersonLocal


@Dao
interface LocalDao {
    @Insert(onConflict = IGNORE)
    suspend fun insert(personLocal: PersonLocal)

    @Query("DELETE FROM PERSON_LOCAL_TABLE")
    suspend fun deleteAll()

    @Delete
    fun delete(personLocal: PersonLocal)

    @Query("SELECT * FROM PERSON_LOCAL_TABLE")
    fun getAllDataFromLocal(): List<PersonLocal>

    @Update(onConflict = REPLACE)
    suspend fun update(personLocal: PersonLocal)

    @Query("DELETE FROM PERSON_LOCAL_TABLE")
    suspend fun deleteTable()
}