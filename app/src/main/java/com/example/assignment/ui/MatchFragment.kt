package com.example.assignment.ui

import com.example.assignment.R
import com.example.assignment.common.baseClasses.BaseFragment
import com.example.assignment.data.model.BaseResponse
import com.example.assignment.data.model.DataState
import com.example.assignment.data.model.Person
import com.example.assignment.data.remote.apiCallAndReciver.PersonListReceiver
import com.example.assignment.databinding.FragmentMatchBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MatchFragment : BaseFragment<FragmentMatchBinding>(), PersonListReceiver {

    override fun getFragmentLayout() = R.layout.fragment_match

    override fun implementApiCallsDataReceiver() {
        apiCallsImplementer.personListReceiver = this
    }

    override fun setUpBindingVariables() {
        apiCallsImplementer.callGetPersonList(matchFragmentViewModelEvent)
    }

    override fun setClickListener() {
        // Not yet implemented"
    }

    override fun onPersonListReceiver(dataState: DataState<BaseResponse<List<Person>>>) {

        when (dataState) {
            is DataState.NetworkError -> {
                showInfoIconMessage(dataState.errorMessage)
            }
            is DataState.GenericError -> {
                dataState.errorMessage?.let { showInfoIconMessage(it) }
            }
            is DataState.Loading -> {
                Timber.e(dataState.toString())
            }
            is DataState.Success -> Timber.e(Gson().toJson(dataState.baseResponseData))
        }
    }
}