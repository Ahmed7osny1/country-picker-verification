package com.example.countrypickerverification.data.repository

import com.example.countrypickerverification.data.localData.CountryDummyData
import com.example.countrypickerverification.data.mapper.toDomain
import com.example.countrypickerverification.domain.model.Country
import com.example.countrypickerverification.domain.repository.CountryRepository

class CountryRepositoryImpl(
    private val countryDummyData: CountryDummyData
) : CountryRepository {

    override suspend fun getCountries(): List<Country> =
        countryDummyData.getCountryList().map { it.toDomain() }

    override suspend fun getCountryByDialCode(dialCode: String): Country? =
        countryDummyData.getCountryList().find {
            it.dialCode == dialCode
        }?.toDomain()

    override suspend fun getCountryByCode(code: String): Country? =
        countryDummyData.getCountryList().find {
            it.code.equals(code, ignoreCase = true)
        }?.toDomain()

    override suspend fun searchCountries(query: String): List<Country> =
        countryDummyData.getCountryList().filter { countryDto ->
            countryDto.name.contains(query, ignoreCase = true) ||
                    countryDto.dialCode.contains(query, ignoreCase = true) ||
                    countryDto.code.contains(query, ignoreCase = true)
        }.map { it.toDomain() }
}