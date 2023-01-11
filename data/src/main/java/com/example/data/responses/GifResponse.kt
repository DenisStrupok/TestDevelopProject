package com.example.data.responses

import com.example.domain.model.*
import com.google.gson.annotations.SerializedName

data class GifResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String?,
    @SerializedName("images")
    val images: ImageResponse
){
    companion object: ModelMapper<Gif, GifResponse>{
        override fun mapTo(model: Gif): GifResponse {
            return with(model){
                GifResponse(
                    id = id,
                    url = url,
                    images = images.let { ImageResponse.mapTo(it) }
                )
            }
        }

        override fun mapToDomain(model: GifResponse): Gif {
            return with(model){
                Gif(
                    id = id,
                    url = url,
                    images = images.let { ImageResponse.mapToDomain(it) }
                )
            }
        }
    }
}

data class ListGifResponse(
    val data: List<GifResponse>?,
    val pagination: PaginationResponse?
) {
    companion object: ModelMapper<GifsModel, ListGifResponse> {
        override fun mapTo(model: GifsModel): ListGifResponse {
            return with(model){
                ListGifResponse(
                    data = data?.map { GifResponse.mapTo(it) },
                    pagination = paginationModel?.let { PaginationResponse.mapTo(it) }
                )
            }
        }

        override fun mapToDomain(model: ListGifResponse): GifsModel {
            return with(model){
                GifsModel(
                    data = data?.map { GifResponse.mapToDomain(it) } as MutableList<Gif>?,
                    paginationModel = pagination?.let { PaginationResponse.mapToDomain(it) }
                )
            }
        }

    }
}

