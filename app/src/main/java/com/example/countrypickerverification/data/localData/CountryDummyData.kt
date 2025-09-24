package com.example.countrypickerverification.data.localData

import com.example.countrypickerverification.data.dto.CountryDto

class CountryDummyData {
    fun getCountryList(): List<CountryDto> {
        return listOf(
            CountryDto("United States", "US", "+1", "https://flagcdn.com/w40/us.png"),
            CountryDto("United Kingdom", "GB", "+44", "https://flagcdn.com/w40/gb.png"),
            CountryDto("Canada", "CA", "+1", "https://flagcdn.com/w40/ca.png"),
            CountryDto("Australia", "AU", "+61", "https://flagcdn.com/w40/au.png"),
            CountryDto("Germany", "DE", "+49", "https://flagcdn.com/w40/de.png"),
            CountryDto("France", "FR", "+33", "https://flagcdn.com/w40/fr.png"),
            CountryDto("Italy", "IT", "+39", "https://flagcdn.com/w40/it.png"),
            CountryDto("Spain", "ES", "+34", "https://flagcdn.com/w40/es.png"),
            CountryDto("Netherlands", "NL", "+31", "https://flagcdn.com/w40/nl.png"),
            CountryDto("Sweden", "SE", "+46", "https://flagcdn.com/w40/se.png"),
            CountryDto("Norway", "NO", "+47", "https://flagcdn.com/w40/no.png"),
            CountryDto("Denmark", "DK", "+45", "https://flagcdn.com/w40/dk.png"),
            CountryDto("Finland", "FI", "+358", "https://flagcdn.com/w40/fi.png"),
            CountryDto("Japan", "JP", "+81", "https://flagcdn.com/w40/jp.png"),
            CountryDto("South Korea", "KR", "+82", "https://flagcdn.com/w40/kr.png"),
            CountryDto("China", "CN", "+86", "https://flagcdn.com/w40/cn.png"),
            CountryDto("India", "IN", "+91", "https://flagcdn.com/w40/in.png"),
            CountryDto("Brazil", "BR", "+55", "https://flagcdn.com/w40/br.png"),
            CountryDto("Mexico", "MX", "+52", "https://flagcdn.com/w40/mx.png"),
            CountryDto("Argentina", "AR", "+54", "https://flagcdn.com/w40/ar.png"),
            CountryDto("Egypt", "EG", "+20", "https://flagcdn.com/w40/eg.png"),
            CountryDto("South Africa", "ZA", "+27", "https://flagcdn.com/w40/za.png"),
            CountryDto("Nigeria", "NG", "+234", "https://flagcdn.com/w40/ng.png"),
            CountryDto("Kenya", "KE", "+254", "https://flagcdn.com/w40/ke.png"),
            CountryDto("Morocco", "MA", "+212", "https://flagcdn.com/w40/ma.png")
        ).sortedBy { it.name }
    }
}