package com.example.testdeveloperproject.data.repositories

import com.example.testdeveloperproject.data.services.GifService
import com.example.testdeveloperproject.domain.repositories.GifRepository

class GifRepositoryImpl(
    private val gifService: GifService
) : GifRepository {

    override suspend fun getGifsList() {
        val tes = gifService.getGifsList("lmLp5f9xrCaT8dRrDiAixYt16ntHnI2M")
        val s = 4
    }
}