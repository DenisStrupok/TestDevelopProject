package com.example.domain.usecases

import com.example.domain.repositories.GifRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GetGifsUseCase(
    private val gifRepository: GifRepository
) {
    operator fun invoke(uiDispatcher: CoroutineScope): Job {
        return uiDispatcher.launch {
            gifRepository.getGifsList()
        }
    }
}