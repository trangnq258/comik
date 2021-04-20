package com.example.comik.data.model

import com.google.gson.annotations.SerializedName

class Price(
    @SerializedName("type")
    val type: String,
    @SerializedName("price")
    val price: Float?
)
