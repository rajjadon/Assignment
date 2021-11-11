package com.example.assignment.data.remote

import com.example.assignment.data.model.DataState
import com.example.assignment.data.remote.apiHelper.SafeApiRequest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MatchFragmentRepo @Inject constructor(
    private val apiService: ApiService,
    private val safeApiRequest: SafeApiRequest
) {

    suspend fun getPersonList() = flow {

        emit(DataState.Loading)
        emit(safeApiRequest.apiRequest { apiService.getPersonList() })
    }
}