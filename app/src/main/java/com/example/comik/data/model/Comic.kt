package com.example.comik.data.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "comic")
data class Comic(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int,
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,
    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String?,
    @Ignore
    @SerializedName("modified")
    var modified: String,
    @Ignore
    @SerializedName("upc")
    val upc: String?,
    @Ignore
    @SerializedName("pageCount")
    val pageCount: Int?,
    @Ignore
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @ColumnInfo(name = "thumbnail")
    @SerializedName("thumbnail")
    val thumbnail: Image?,
    @Ignore
    @SerializedName("images")
    val image: List<Image>?,
    @Ignore
    @SerializedName("prices")
    val prices: List<Price>?,
    @Ignore
    @SerializedName("dates")
    val dates: List<Date>
) {

    constructor(id: Int, title: String?, description: String?, thumbnail: Image?) : this(
        id,
        title,
        description,
        "",
        null,
        null,
        null,
        thumbnail,
        null,
        null,
        listOf()
    )

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Comic>() {
            override fun areItemsTheSame(oldItem: Comic, newItem: Comic) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Comic, newItem: Comic) = oldItem == newItem
        }
    }
}
