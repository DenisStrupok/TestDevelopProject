package com.example.testdeveloperproject.data.responses

import com.google.gson.annotations.SerializedName

data class GifResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String?
)

data class ListGifResponse(
    val data: List<GifResponse>
)

