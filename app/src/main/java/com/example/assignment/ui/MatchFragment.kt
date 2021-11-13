package com.example.assignment.ui

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.common.baseClasses.BaseFragment
import com.example.assignment.data.model.BaseResponse
import com.example.assignment.data.model.DataState
import com.example.assignment.data.model.PersonLocal
import com.example.assignment.data.remote.apiCallAndReciver.PersonListReceiver
import com.example.assignment.databinding.FragmentMatchBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MatchFragment : BaseFragment<FragmentMatchBinding>(), PersonListReceiver, MatchOperation {

    private lateinit var matchOperation: MatchOperation
    private lateinit var personListAdapter: PersonListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun getFragmentLayout() = R.layout.fragment_match

    override fun implementApiCallsDataReceiver() {
        apiCallsImplementer.personListReceiver = this
        matchOperation = this
        personListAdapter = PersonListAdapter(matchOperation)
    }

    override fun setUpBindingVariables() {
        apiCallsImplementer.callGetPersonList(matchFragmentViewModelEvent, resultParams)
    }

    override fun setClickListener() {
        linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvMatch.layoutManager = linearLayoutManager
        binding.rvMatch.addOnScrollListener(addPagination())
    }

    private fun addPagination() = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy > 0) { //check for scroll down

                lifecycleScope.launchWhenResumed {

                    binding.rvMatch.layoutManager?.let {

                        val visibleItemCount = linearLayoutManager.childCount
                        val totalItemCount = linearLayoutManager.itemCount
                        val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()

                        if (visibleItemCount + pastVisibleItems >= totalItemCount)

                            apiCallsImplementer.callGetPersonList(
                                matchFragmentViewModelEvent,
                                resultParams.apply { resultKeyword += 10 })
                    }
                }
            }
        }
    }

    override fun onPersonListReceiver(dataState: DataState<BaseResponse<List<PersonLocal>>>) {

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
            is DataState.Success -> {

                dataState.baseResponseData.data?.let {
                    personListAdapter.addItems(it)
                    binding.adapter = personListAdapter
                } ?: run { showInfoIconMessage(getString(R.string.no_data)) }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        resultParams.onDataClear()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun acceptRequest(person: PersonLocal) {
        personListAdapter.items.add(person.apply {
            requestTittle = getString(R.string.accept_text)
        })
        personListAdapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun declineRequest(person: PersonLocal) {
        personListAdapter.items.add(person.apply {
            requestTittle = getString(R.string.decline_text)
        })
        personListAdapter.notifyDataSetChanged()
    }
}