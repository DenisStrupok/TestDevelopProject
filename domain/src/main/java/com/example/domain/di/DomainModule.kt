package com.example.domain.di

import com.example.domain.usecases.FindGifByNameUseCase
import com.example.domain.usecases.GetRandomListGifsUseCase
import org.koin.dsl.module

private val useCasesModule = module {
    factory { GetRandomListGifsUseCase(get()) }
    factory { FindGifByNameUseCase(get()) }
}

val domainModule = arrayOf(useCasesModule)