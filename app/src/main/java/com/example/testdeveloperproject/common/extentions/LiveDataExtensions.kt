package com.example.testdeveloperproject.common.extentions

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.forceRefresh() {
    this.value = this.value
}