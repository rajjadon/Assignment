package com.example.assignment.ui

import androidx.core.content.ContextCompat
import com.example.assignment.R
import com.example.assignment.common.baseClasses.BaseRecyclerViewAdapter
import com.example.assignment.common.utills.showImageInImageView
import com.example.assignment.data.model.PersonLocal
import com.example.assignment.databinding.PersonListItemBinding

class PersonListAdapter(private val matchOperation: MatchOperation) :
    BaseRecyclerViewAdapter<PersonLocal, PersonListItemBinding>() {

    override fun getLayout() = R.layout.person_list_item

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<PersonListItemBinding>,
        position: Int
    ) {
        holder.binding.person = items[position]
        showImageInImageView(holder.binding.userImage, items[position].userPicture)

        holder.binding.matchOperation = matchOperation

        if (items[position].requestTittle.equals(
                holder.itemView.context.getString(R.string.accept_text),
                true
            )
        )
            holder.binding.tvMessage.setTextColor(
                ContextCompat.getColorStateList(
                    holder.itemView.context,
                    R.color.green
                )
            )
        else if (items[position].requestTittle.equals(
                holder.itemView.context.getString(R.string.decline_text),
                true
            )
        )

            holder.binding.tvMessage.setTextColor(
                ContextCompat.getColorStateList(
                    holder.itemView.context,
                    R.color.red
                )
            )
    }
}