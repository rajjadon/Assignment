package com.example.assignment.data.remote.apiCallAndReciver

import com.example.assignment.common.MarkerInterface
import com.example.assignment.data.model.BaseResponse
import com.example.assignment.data.model.DataState
import com.example.assignment.data.model.PersonLocal

interface PersonListReceiver : MarkerInterface {
    fun onPersonListReceiver(dataState: DataState<BaseResponse<List<PersonLocal>>>)
}