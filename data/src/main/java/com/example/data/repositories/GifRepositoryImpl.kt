package com.example.data.repositories

import com.example.data.BuildConfig
import com.example.data.responses.ListGifResponse
import com.example.data.services.GifService
import com.example.domain.model.GifsModel
import com.example.domain.repositories.GifRepository

class GifRepositoryImpl(
    private val gifService: GifService,
) : GifRepository {

    override suspend fun getGifsList(limit: Int, offset: Int): GifsModel {
        return try {
            ListGifResponse.mapToDomain(
                gifService.getGifsList(
                    key = BuildConfig.API_KEY,
                    limit = limit,
                    offset = offset
                )
            )
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun findGifsByName(name: String, limit: Int, offset: Int): GifsModel {
        return try {
            ListGifResponse.mapToDomain(
                gifService.findGifsByName(
                    key = BuildConfig.API_KEY,
                    name = name,
                    limit = limit,
                    offset = offset
                )
            )
        } catch (ex: Exception) {
            throw ex
        }
    }
}
