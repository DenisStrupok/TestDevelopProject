package com.example.domain.di

import com.example.domain.usecases.GetGifsUseCase
import org.koin.dsl.module

private val useCasesModule = module {
    factory { GetGifsUseCase(get()) }
}

val domainModule = arrayOf(useCasesModule)