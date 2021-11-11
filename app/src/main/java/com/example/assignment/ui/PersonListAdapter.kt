package com.example.assignment.ui

import com.example.assignment.R
import com.example.assignment.common.baseClasses.BaseRecyclerViewAdapter
import com.example.assignment.data.model.Person
import com.example.assignment.databinding.PersonListItemBinding

class PersonListAdapter(private val matchOperation: MatchOperation) :
    BaseRecyclerViewAdapter<Person, PersonListItemBinding>() {

    override fun getLayout() = R.layout.person_list_item

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<PersonListItemBinding>,
        position: Int
    ) {
        holder.binding.person = items[position]
        holder.binding.matchOperation = matchOperation
    }
}