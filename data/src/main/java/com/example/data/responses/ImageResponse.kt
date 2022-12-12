package com.example.data.responses

import com.example.domain.model.ImageModel
import com.example.domain.model.ModelMapper
import com.example.domain.model.Original
import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("original")
    val original: OriginalResponse?
) {
    companion object : ModelMapper<ImageModel, ImageResponse> {


        override fun mapTo(model: ImageModel): ImageResponse {
            return with(model){
                ImageResponse(
                    original = original?.let { OriginalResponse.mapTo(it) }
                )
            }
        }

        override fun mapToDomain(model: ImageResponse): ImageModel {
            return with(model){
                ImageModel(
                    original = original?.let { OriginalResponse.mapToDomain(it) }
                )
            }
        }
    }
}

data class OriginalResponse(
    val height: String?,
    val width: String?,
    val size: String?,
    val url: String?,
){
    companion object : ModelMapper<Original, OriginalResponse> {
        override fun mapTo(model: Original): OriginalResponse {
            return with(model){
                OriginalResponse(
                    height = model.height,
                    width = model.width,
                    size = model.size,
                    url = model.url
                )
            }
        }

        override fun mapToDomain(model: OriginalResponse): Original {
            return with(model){
                Original(
                    height = model.height,
                    width = model.width,
                    size = model.size,
                    url = model.url
                )
            }
        }
    }
}