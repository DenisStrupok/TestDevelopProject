package com.example.domain.model

class Gif(
    val id: String,
    val url: String?,
    val images: ImageModel
)

data class ImageModel(
    val original: Original?
)

data class Original(
    val height: String?,
    val width: String?,
    val size: String?,
    val url: String?,
)