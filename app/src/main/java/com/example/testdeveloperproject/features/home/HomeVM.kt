package com.example.testdeveloperproject.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.GifsModel
import com.example.domain.usecases.FindGifByNameUseCase
import com.example.domain.usecases.GetGifsUseCase
import com.example.domain.usecases.ResultCallbacks

class HomeVM(
    private val getGifsListUseCase: GetGifsUseCase,
    private val findGifByNameUseCase: FindGifByNameUseCase
) : ViewModel() {

    companion object {
        const val LIMIT_PAGE_SIZE = 10
        const val MINIMAL_SIZE_NAME = 2
    }

    val gifs = MutableLiveData<GifsModel?>()
    val isEmptyGifsList = MutableLiveData<Boolean>()

    private var _nameGif = MutableLiveData<String>()
    val nameGif: LiveData<String> get() = _nameGif

    fun getGifsList(offset: Int = 0) {
        getGifsListUseCase.invoke(
            uiDispatcher = viewModelScope,
            ResultCallbacks(
                onSuccess = { gifs ->
                    this.gifs.value = gifs
                }
            ),
            params = GetGifsUseCase.Params(
                limit = LIMIT_PAGE_SIZE,
                offset = offset
            )
        )
    }

    fun findGifByName(nameGif: CharSequence?) {
        _nameGif.value = nameGif.toString()
        if ((nameGif?.length ?: 0) > MINIMAL_SIZE_NAME) {
            gifs.value?.data?.clear()
            isEmptyGifsList.value = true
            findGifByNameUseCase.invoke(
                uiDispatcher = viewModelScope,
                ResultCallbacks(
                    onSuccess = { gifs ->
                        this.gifs.value = gifs
                    },
                ),
                params = FindGifByNameUseCase.Params(
                    name = this._nameGif.value!!,
                    limit = LIMIT_PAGE_SIZE,
                    offset = 10
                )
            )
        }

    }
}