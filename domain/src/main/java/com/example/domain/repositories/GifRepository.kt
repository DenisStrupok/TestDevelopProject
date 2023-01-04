package com.example.domain.repositories

import com.example.domain.model.GifsModel

interface GifRepository {

    suspend fun getGifsList(limit: Int, offset: Int): GifsModel

    suspend fun findGifsByName(name: String, offset: Int, limit: Int): GifsModel
}