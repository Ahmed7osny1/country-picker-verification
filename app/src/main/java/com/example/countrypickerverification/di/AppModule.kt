package com.example.countrypickerverification.di

import org.koin.dsl.module

val appModule = module {
    includes(
        dataModule,
        viewModelModule
    )
}