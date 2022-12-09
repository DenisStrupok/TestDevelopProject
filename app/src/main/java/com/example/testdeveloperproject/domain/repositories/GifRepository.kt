package com.example.testdeveloperproject.domain.repositories

interface GifRepository {

  //  suspend fun getRandomGif()

    suspend fun getGifsList()
}