package com.example.testdeveloperproject.features.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testdeveloperproject.common.body.GifsBody
import com.example.testdeveloperproject.common.model.GifModel

class DetailVM: ViewModel() {

    val gifs = MutableLiveData<GifsBody>()
    val position = MutableLiveData<Int>()


    fun getData(gifsList: GifsBody) {
        gifs.value = gifsList
        val test = gifs.value
        test.toString()
        checkSelectedGif()
    }

    private fun checkSelectedGif(){
        gifs.value?.forEachIndexed { index, gif ->
            if (gif.isSelectedGif){
                position.value = index
            }
        }
    }
}