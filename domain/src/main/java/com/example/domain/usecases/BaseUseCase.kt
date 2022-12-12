package com.example.domain.usecases

import kotlinx.coroutines.*

abstract class BaseUseCase<RESULT, PARAMS>: UseCase<RESULT, PARAMS> {

    operator fun invoke (uiDispatcher: CoroutineScope,
    result: ResultCallbacks<RESULT>,
    params: PARAMS) : Job{
        return uiDispatcher.launch {
            withContext(Dispatchers.Main) {
                result.onLoading?.invoke(true)
                try {
                    val resultOfWork = remoteWork(params)
                    result.onSuccess?.invoke(resultOfWork)
                    result.onLoading?.invoke(false)
                } catch (e: Throwable) {
                    result.onLoading?.invoke(false)
                }
            }
        }
    }

}

class ResultCallbacks<T>(
    val onSuccess: ((T) -> Unit)? = null,
    val onLoading: ((Boolean) -> Unit)? = null,
)


interface UseCase<RESULT, PARAMS> {
    suspend fun remoteWork(params: PARAMS?): RESULT

}
