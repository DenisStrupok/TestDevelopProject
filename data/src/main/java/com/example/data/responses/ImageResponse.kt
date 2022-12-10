package com.example.data.responses

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("id")
    var id: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("width")
    val width: Int?
)