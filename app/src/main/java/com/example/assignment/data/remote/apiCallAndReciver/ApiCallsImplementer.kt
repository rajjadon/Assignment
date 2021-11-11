package com.example.assignment.data.remote.apiCallAndReciver

import com.example.assignment.common.Utills.IsLoadingEvent
import com.example.assignment.data.model.BaseResponse
import com.example.assignment.data.model.DataState
import com.example.assignment.data.model.Person
import com.example.assignment.ui.MatchFragmentViewModel
import org.greenrobot.eventbus.EventBus

class ApiCallsImplementer(private val isLoadingEvent: IsLoadingEvent) : ApiCallAndReceiverHandler {

    lateinit var personListReceiver: PersonListReceiver

    override fun callGetPersonList(matchFragmentViewModelEvent: MatchFragmentViewModel) {
        isLoading(true)
        matchFragmentViewModelEvent.setStateEvent(MatchFragmentViewModel.MatchFragmentViewModelEvent.GetPersonDetails)
    }

    override fun personListResponse(person: DataState<BaseResponse<List<Person>>>) {
        isLoading(false)
        if (this::personListReceiver.isInitialized)
            personListReceiver.onPersonListReceiver(person)
    }

    private fun isLoading(isLoading: Boolean) {
        isLoadingEvent.value = isLoading
        EventBus.getDefault().post(isLoadingEvent)
    }
}