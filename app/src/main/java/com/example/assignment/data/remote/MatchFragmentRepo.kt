package com.example.assignment.data.remote

import com.example.assignment.data.model.DataState
import com.example.assignment.data.model.ResultParams
import com.example.assignment.data.remote.apiHelper.SafeApiRequest
import com.example.assignment.data.remote.mapper.PersonListMapper
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MatchFragmentRepo @Inject constructor(
    private val apiService: ApiService,
    private val safeApiRequest: SafeApiRequest,
    private val personListMapper: PersonListMapper
) {

    suspend fun getPersonList(resultParams: ResultParams) = flow {

        emit(DataState.Loading)
        emit(safeApiRequest.apiRequest {
            personListMapper.mapFromEntity(
                apiService.getPersonList(
                    resultParams.resultKeyword
                )
            )
        })
    }
}