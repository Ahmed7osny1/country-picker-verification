package com.example.countrypickerverification.domain.repository

import com.example.countrypickerverification.domain.model.Country

interface CountryRepository {
    suspend fun getCountries(): List<Country>
    suspend fun getCountryByDialCode(dialCode: String): Country?
    suspend fun getCountryByCode(code: String): Country?
    suspend fun searchCountries(query: String): List<Country>
}