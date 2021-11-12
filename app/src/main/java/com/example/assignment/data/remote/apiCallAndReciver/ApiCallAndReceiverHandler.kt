package com.example.assignment.data.remote.apiCallAndReciver

import com.example.assignment.common.MarkerInterface
import com.example.assignment.data.model.BaseResponse
import com.example.assignment.data.model.DataState
import com.example.assignment.data.model.Person
import com.example.assignment.data.model.ResultParams
import com.example.assignment.ui.MatchFragmentViewModel

interface ApiCallAndReceiverHandler : MarkerInterface {

    fun callGetPersonList(
        matchFragmentViewModelEvent: MatchFragmentViewModel,
        resultParams: ResultParams
    )

    fun personListResponse(person: DataState<BaseResponse<List<Person>>>)
}