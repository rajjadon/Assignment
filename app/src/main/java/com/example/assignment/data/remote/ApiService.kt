package com.example.assignment.data.remote

import com.example.assignment.BuildConfig
import com.example.assignment.common.MarkerInterface
import com.example.assignment.data.model.BaseResponse
import com.example.assignment.data.model.Person
import com.example.assignment.data.remote.NetworkingConstant.API_RESULT
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService : MarkerInterface {

    @GET(BuildConfig.RESULT)
    suspend fun getPersonList(@Query(API_RESULT) result: Int): BaseResponse<List<Person>>
}