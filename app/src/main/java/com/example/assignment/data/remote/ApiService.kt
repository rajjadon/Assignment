package com.example.assignment.data.remote

import com.example.assignment.BuildConfig
import com.example.assignment.common.MarkerInterface
import com.example.assignment.data.model.Person
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService : MarkerInterface {

    @GET
    suspend fun getPersonList(@Query(BuildConfig.RESULT) result: Int): List<Person>
}