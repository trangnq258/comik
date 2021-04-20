package com.example.comik.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.comik.R
import com.example.comik.base.BaseAdapter
import com.example.comik.base.BaseViewHolder
import com.example.comik.data.model.Date
import com.example.comik.databinding.ItemDateBinding

class DateAdapter : BaseAdapter<Date, DateAdapter.DateViewHolder>(Date.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DateViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_date, parent, false
            )
        )

    class DateViewHolder(
        private val itemDateBinding: ItemDateBinding
    ) : BaseViewHolder<Date>(itemDateBinding, {}) {

        override fun bindData(item: Date) {
            super.bindData(item)
            itemDateBinding.date = item
        }
    }
}
