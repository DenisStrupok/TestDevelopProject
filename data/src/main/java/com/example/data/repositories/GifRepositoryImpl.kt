package com.example.data.repositories

import com.example.data.responses.ListGifResponse
import com.example.data.services.GifService
import com.example.domain.model.GifsModel
import com.example.domain.repositories.GifRepository

class GifRepositoryImpl(
    private val gifService: GifService
) : GifRepository {

    override suspend fun getGifsList(limit: Int, offset: Int): GifsModel {
       return try {
         ListGifResponse.mapToDomain(gifService.getGifsList("lmLp5f9xrCaT8dRrDiAixYt16ntHnI2M", limit = limit, offset = offset))
        }
       catch (ex: Exception){
           throw ex
        }
    }

    override suspend fun findGifsByName(name: String, limit: Int, offset: Int): GifsModel {
       return try {
           ListGifResponse.mapToDomain(
               gifService.findGifsByName("lmLp5f9xrCaT8dRrDiAixYt16ntHnI2M", name = name,
                   limit = limit,
               offset = offset ))
       }catch (ex: Exception){
           throw ex
       }
    }
}
