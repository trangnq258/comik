package com.example.comik.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Story(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("thumbnail")
    val thumbnail: Image
) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Story>() {
            override fun areItemsTheSame(oldItem: Story, newItem: Story) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Story, newItem: Story) = oldItem == newItem
        }
    }
}
