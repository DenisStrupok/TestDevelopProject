package com.example.testdeveloperproject.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Gif
import com.example.domain.usecases.FindGifByNameUseCase
import com.example.domain.usecases.GetRandomListGifsUseCase
import com.example.domain.usecases.ResultCallbacks
import com.example.testdeveloperproject.common.body.GifBody
import com.example.testdeveloperproject.common.body.GifsBody
import com.example.testdeveloperproject.common.extentions.forceRefresh

class HomeVM(
    private val getRandomListGifsUseCase: GetRandomListGifsUseCase,
    private val findGifByNameUseCase: FindGifByNameUseCase
) : ViewModel() {

    companion object {
        const val LIMIT_PAGE_SIZE = 10
        const val MINIMAL_SIZE_NAME = 2
    }

    val gifs = MutableLiveData<MutableList<Gif>?>()
    val randomGifs = MutableLiveData<MutableList<Gif>?>()
    val gifsBody = MutableLiveData<GifsBody>()
    val isEmptyGifsList = MutableLiveData<Boolean>()

    private var _nameGif = MutableLiveData<String>()
    val nameGif: LiveData<String> get() = _nameGif

    fun getRandomGifs(offset: Int = 0) {
        getRandomListGifsUseCase.invoke(
            uiDispatcher = viewModelScope,
            ResultCallbacks(
                onSuccess = { gifs ->
                    if (randomGifs.value?.isNotEmpty() == true) {
                        gifs.data?.let { listGifs -> randomGifs.value?.addAll(listGifs) }
                        randomGifs.forceRefresh()
                    } else {
                        randomGifs.value = gifs.data
                    }
                }
            ),
            params = GetRandomListGifsUseCase.Params(
                limit = LIMIT_PAGE_SIZE,
                offset = offset
            )
        )
    }

    fun findGifByName(nameGif: CharSequence?, offset: Int = 10) {
        _nameGif.value = nameGif.toString()
        if ((nameGif?.length ?: 0) > MINIMAL_SIZE_NAME) {
            isEmptyGifsList.value = true
            findGifByNameUseCase.invoke(
                uiDispatcher = viewModelScope,
                ResultCallbacks(
                    onSuccess = { gifs ->
                        if (this.gifs.value?.isNotEmpty() == true) {
                            gifs.data?.let { listGifs -> this.gifs.value?.addAll(listGifs) }
                            this.gifs.forceRefresh()
                        } else {
                            this.gifs.value = gifs.data
                        }
                    },
                ),
                params = FindGifByNameUseCase.Params(
                    name = this._nameGif.value!!,
                    limit = LIMIT_PAGE_SIZE,
                    offset = offset
                )
            )
        }
    }

    fun changeSelectedGif(selectedGifId: String) {
        if (nameGif.value.isNullOrEmpty()) {
            randomGifs.value?.forEach { gif ->
                if (gif.id == selectedGifId) {
                    gif.isSelectedGif = true
                }
            }
        } else {
            this.gifs.value?.forEach { gif ->
                if (gif.id == selectedGifId) {
                    gif.isSelectedGif = true
                }
            }
        }
        checkListGifs()
    }

    private fun checkListGifs() {
        if (nameGif.value.isNullOrEmpty()) {
            gifsBody.value = changeList(randomGifs.value)
        } else {
            gifsBody.value = changeList(gifs.value)
        }
    }

    private fun changeList(list: MutableList<Gif>?): GifsBody {
        val gifBody = GifsBody()
        list?.forEach { gif ->
            val newGif = GifBody(
                id = gif.id,
                url = gif.url,
                images = gif.images,
                isSelectedGif = gif.isSelectedGif
            )
            gifBody.add(newGif)
        }
        return gifBody
    }
}