package com.example.countrypickerverification.di

import com.example.countrypickerverification.data.localData.CountryDummyData
import com.example.countrypickerverification.data.repository.CountryRepositoryImpl
import com.example.countrypickerverification.domain.repository.CountryRepository
import org.koin.dsl.module

val dataModule = module {
    single { CountryDummyData() }
    single<CountryRepository> { CountryRepositoryImpl(get()) }
}