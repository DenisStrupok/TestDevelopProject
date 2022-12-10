package com.example.data.responses

import com.example.domain.model.Gif
import com.example.domain.model.GifsModel
import com.example.domain.model.ModelMapper
import com.google.gson.annotations.SerializedName

data class GifResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String?
){
    companion object: ModelMapper<Gif, GifResponse>{
        override fun mapTo(model: Gif): GifResponse {
            return with(model){
                GifResponse(
                    id = model.id,
                    url = model.url
                )
            }
        }

        override fun mapToDomain(model: GifResponse): Gif {
            return with(model){
                Gif(
                    id = model.id,
                    url = model.url
                )
            }
        }
    }
}

data class ListGifResponse(
    val data: List<GifResponse>?
) {
    companion object: ModelMapper<GifsModel, ListGifResponse> {
        override fun mapTo(model: GifsModel): ListGifResponse {
            return with(model){
                ListGifResponse(
                    data = data?.map { GifResponse.mapTo(it) }
                )
            }
        }

        override fun mapToDomain(model: ListGifResponse): GifsModel {
            return with(model){
                GifsModel(
                    data = data?.map { GifResponse.mapToDomain(it) }
                )
            }
        }

    }
}

