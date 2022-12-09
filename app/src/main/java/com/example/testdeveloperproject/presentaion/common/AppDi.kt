package com.example.testdeveloperproject.presentaion.common

import com.example.testdeveloperproject.data.repositories.GifRepositoryImpl
import com.example.testdeveloperproject.data.services.GifService
import com.example.testdeveloperproject.domain.repositories.GifRepository
import com.example.testdeveloperproject.domain.usecases.GetGifsUseCase
import com.example.testdeveloperproject.presentaion.features.home.HomeVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val viewModelsModule = module {
    viewModel<HomeVM>()
}

private val useCasesModule = module {
    factory { GetGifsUseCase(get()) }
}

private val repositoriesModule = module {
    factory<GifRepository> { GifRepositoryImpl(get()) }
}

private val serviceModule = module {
    single<GifService> {
        (get() as Retrofit).create(GifService::class.java)
    }
}

private val networkModule = module {

    factory {
        Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

val appModule = arrayOf(viewModelsModule, useCasesModule, repositoriesModule,  networkModule, serviceModule)