package com.example.testdeveloperproject.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.GifsModel
import com.example.domain.usecases.GetGifsUseCase
import com.example.domain.usecases.ResultCallbacks

class HomeVM(private val getGifsListUseCase: GetGifsUseCase): ViewModel() {

    companion object {
        const val LIMIT_PAGE_SIZE = 10
    }

    val gifs = MutableLiveData<GifsModel>()

    fun getGifsList(){
        val result = getGifsListUseCase.invoke(
            uiDispatcher = viewModelScope,
        result = ResultCallbacks(
            onSuccess = { gifs ->
                this.gifs.value = gifs
            }
        ),
            params = GetGifsUseCase.Params(
                limit = LIMIT_PAGE_SIZE,
                offset = 0
            )
        )
        result.toString()
    }
}