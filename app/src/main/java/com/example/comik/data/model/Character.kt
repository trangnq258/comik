package com.example.comik.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("thumbnail")
    val thumbnail: Image
) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character) =
                oldItem == newItem
        }
    }
}
