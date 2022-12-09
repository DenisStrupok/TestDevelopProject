package com.example.testdeveloperproject.presentaion.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdeveloperproject.domain.usecases.GetGifsUseCase

class HomeVM(private val getGifsListUseCase: GetGifsUseCase): ViewModel() {

    fun getGifsList(){
        getGifsListUseCase.invoke(viewModelScope)
    }
}