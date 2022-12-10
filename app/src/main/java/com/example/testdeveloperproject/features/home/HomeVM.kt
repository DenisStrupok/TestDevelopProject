package com.example.testdeveloperproject.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Gif
import com.example.domain.usecases.GetGifsUseCase

class HomeVM(private val getGifsListUseCase: GetGifsUseCase): ViewModel() {

    val gifs = MutableLiveData<MutableList<Gif>>()

    suspend fun getGifsList(){
        val result = getGifsListUseCase.invoke(viewModelScope)
        result.toString()
    }
}