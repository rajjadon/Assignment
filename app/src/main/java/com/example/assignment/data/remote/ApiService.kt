package com.example.assignment.data.remote

import com.example.assignment.BuildConfig
import com.example.assignment.common.MarkerInterface
import com.example.assignment.data.model.BaseResponse
import com.example.assignment.data.model.Person
import retrofit2.http.GET

interface ApiService : MarkerInterface {

    @GET(BuildConfig.RESULT)
    suspend fun getPersonList(): BaseResponse<List<Person>>
}