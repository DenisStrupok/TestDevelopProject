package com.example.data.services

import com.example.data.responses.ListGifResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GifService {

    @GET("gifs/trending")
    suspend fun getGifsList(@Query("api_key") key: String, @Query("limit") limit: Int): ListGifResponse
}