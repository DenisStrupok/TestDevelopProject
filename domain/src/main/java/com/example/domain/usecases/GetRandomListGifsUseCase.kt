package com.example.domain.usecases

import com.example.domain.model.GifsModel
import com.example.domain.repositories.GifRepository
import kotlinx.coroutines.*

class GetRandomListGifsUseCase(
    private val gifRepository: GifRepository
): BaseUseCase<GifsModel, GetRandomListGifsUseCase.Params>() {

    override suspend fun remoteWork(params: Params?): GifsModel {
        return withContext(Dispatchers.IO){
            gifRepository.getRandomListGifs(
                params!!.limit,
                params.offset
            )
        }
    }

    class Params(
        val limit: Int,
        val offset: Int
    )

}