package com.example.assignment.ui

import com.example.assignment.R
import com.example.assignment.common.baseClasses.BaseFragment
import com.example.assignment.data.model.BaseResponse
import com.example.assignment.data.model.DataState
import com.example.assignment.data.model.Person
import com.example.assignment.data.remote.apiCallAndReciver.PersonListReceiver
import com.example.assignment.databinding.FragmentMatchBinding

class MatchFragment : BaseFragment<FragmentMatchBinding>(), PersonListReceiver {

    override fun getFragmentLayout() = R.layout.fragment_match

    override fun implementApiCallsDataReceiver() {
        apiCallsImplementer.personListReceiver = this
    }

    override fun setUpBindingVariables() {
        TODO("Not yet implemented")
    }

    override fun setClickListener() {
        TODO("Not yet implemented")
    }

    override fun onPersonListReceiver(dataState: DataState<BaseResponse<List<Person>>>) {
        TODO("Not yet implemented")
    }
}