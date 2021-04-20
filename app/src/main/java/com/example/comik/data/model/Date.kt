package com.example.comik.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

class Date(
    @SerializedName("type")
    val type: String,
    @SerializedName("date")
    var date: String
) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Date>() {
            override fun areItemsTheSame(oldItem: Date, newItem: Date) =
                oldItem.type == newItem.type

            override fun areContentsTheSame(oldItem: Date, newItem: Date) =
                oldItem.toString() == newItem.toString()
        }
    }
}
