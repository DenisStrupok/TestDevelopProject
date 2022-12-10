package com.example.testdeveloperproject.common

import com.example.testdeveloperproject.features.home.HomeVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelsModule = module {
    viewModel<HomeVM>()
}
val appModule = arrayOf(viewModelsModule)