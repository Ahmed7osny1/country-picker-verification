package com.example.countrypickerverification.data.mapper

import com.example.countrypickerverification.data.dto.CountryDto
import com.example.countrypickerverification.domain.model.Country

fun CountryDto.toDomain(): Country {
    return Country(
        name = name,
        code = code,
        dialCode = dialCode,
        flagUrl = flagUrl
    )
}