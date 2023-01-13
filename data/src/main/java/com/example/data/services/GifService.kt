package com.example.data.services

import com.example.data.responses.ListGifResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GifService {

    @GET("gifs/trending")
    suspend fun getRandomListGifsList(
        @Query("api_key") key: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ListGifResponse

    @GET("gifs/search")
    suspend fun findGifsByName(
        @Query("api_key") key: String,
        @Query("q") name: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ListGifResponse
}