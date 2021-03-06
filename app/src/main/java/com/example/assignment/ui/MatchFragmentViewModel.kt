package com.example.assignment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.common.utills.Event
import com.example.assignment.data.model.BaseResponse
import com.example.assignment.data.model.DataState
import com.example.assignment.data.model.PersonLocal
import com.example.assignment.data.model.ResultParams
import com.example.assignment.data.remote.MatchFragmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchFragmentViewModel @Inject constructor
    (
    private val matchFragmentRepo: MatchFragmentRepo
) : ViewModel() {


    val getPersonList: LiveData<Event<DataState<BaseResponse<List<PersonLocal>>>>>
        get() = _getPersonList

    private val _getPersonList: MutableLiveData<Event<DataState<BaseResponse<List<PersonLocal>>>>> =
        MutableLiveData()

    fun setStateEvent(matchFragmentViewModelEvent: MatchFragmentViewModelEvent) {
        viewModelScope.launch {
            when (matchFragmentViewModelEvent) {
                is MatchFragmentViewModelEvent.GetPersonDetails -> {

                    matchFragmentRepo.getPersonList(matchFragmentViewModelEvent.resultParams)
                        .onEach { dataState ->

                            _getPersonList.value = Event(dataState)
                        }.launchIn(viewModelScope)
                }
            }
        }
    }

    sealed class MatchFragmentViewModelEvent {

        class GetPersonDetails(val resultParams: ResultParams) : MatchFragmentViewModelEvent()
    }
}