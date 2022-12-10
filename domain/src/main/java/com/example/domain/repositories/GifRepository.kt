package com.example.domain.repositories

import com.example.domain.model.GifsModel

interface GifRepository {

    suspend fun getGifsList(): GifsModel
}