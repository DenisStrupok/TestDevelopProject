package com.example.domain.di

import com.example.domain.usecases.FindGifByNameUseCase
import com.example.domain.usecases.GetGifsUseCase
import org.koin.dsl.module

private val useCasesModule = module {
    factory { GetGifsUseCase(get()) }
    factory { FindGifByNameUseCase(get()) }
}

val domainModule = arrayOf(useCasesModule)