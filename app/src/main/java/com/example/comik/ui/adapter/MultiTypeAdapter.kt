package com.example.comik.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.example.comik.R
import com.example.comik.BR
import com.example.comik.base.BaseAdapter
import com.example.comik.base.BaseViewHolder
import com.example.comik.data.model.Character
import com.example.comik.data.model.Event
import com.example.comik.data.model.Series
import com.example.comik.data.model.Story

const val VIEW_TYPE_EVENT = 0
const val VIEW_TYPE_CHARACTER = 1
const val VIEW_TYPE_STORY = 2
const val VIEW_TYPE_SERIES = 3
const val VIEW_TYPE_CREATOR = 4

class MultiTypeAdapter<T>(
    private val onItemClickListener: (T) -> Unit
) : BaseAdapter<T, MultiTypeAdapter.TypeViewHolder<T>>(DiffUtilType()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        VIEW_TYPE_EVENT -> initViewHolder(R.layout.item_event, parent)
        VIEW_TYPE_CHARACTER -> initViewHolder(R.layout.item_character, parent)
        VIEW_TYPE_STORY -> initViewHolder(R.layout.item_story, parent)
        VIEW_TYPE_SERIES -> initViewHolder(R.layout.item_series, parent)
        else -> initViewHolder(R.layout.item_creator, parent)
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is Event -> VIEW_TYPE_EVENT
        is Character -> VIEW_TYPE_CHARACTER
        is Story -> VIEW_TYPE_STORY
        is Series -> VIEW_TYPE_SERIES
        else -> VIEW_TYPE_CREATOR
    }

    private fun initViewHolder(
        layout: Int,
        parent: ViewGroup
    ): TypeViewHolder<T> =
        TypeViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layout, parent, false),
            onItemClickListener
        )

    class DiffUtilType<T> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem.contentsTheSame(newItem)

        private fun T.contentsTheSame(newItem: T) = toString() == newItem.toString()
    }

    class TypeViewHolder<T>(
        private val itemDataBinding: ViewDataBinding,
        onClickItem: (T) -> Unit
    ) : BaseViewHolder<T>(itemDataBinding, onClickItem) {

        override fun bindData(item: T) {
            super.bindData(item)
            itemDataBinding.setVariable(BR.item, item)
        }
    }
}
