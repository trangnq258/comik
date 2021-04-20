package com.example.comik.base

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import java.util.concurrent.Executors

abstract class BaseAdapter<T, VH : BaseViewHolder<T>>(
    diffUtil: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(
    AsyncDifferConfig.Builder<T>(diffUtil)
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
), UpdateDataAdapter<T> {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun setData(data: List<T>?) {
        submitList(data)
    }
}
