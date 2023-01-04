package com.example.domain.usecases

import com.example.domain.model.GifsModel
import com.example.domain.repositories.GifRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FindGifByNameUseCase(
    private val gifRepository: GifRepository
) : BaseUseCase<GifsModel, FindGifByNameUseCase.Params>() {

    override suspend fun remoteWork(params: Params?): GifsModel {
        return withContext(Dispatchers.IO) {
            gifRepository.findGifsByName(
                name = params!!.name,
                offset = params.offset,
                limit = params.limit
            )
        }
    }

    class Params(
        val name: String,
        val limit: Int,
        val offset: Int
    )
}