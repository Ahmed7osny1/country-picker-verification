package com.example.countrypickerverification.presentation.screen.sign_up

import com.example.countrypickerverification.domain.model.Country

data class SignupUiState(
    val isLoading: Boolean = false,
    val isLoadingCountries: Boolean = false,

    val countries: List<Country> = emptyList(),
    val filteredCountries: List<Country> = emptyList(),
    val searchQuery: String = "",
    val isDropdownVisible: Boolean = false,

    val selectedCountry: Country? = null,
    val phoneNumber: String = "",
    val agreedToTerms: Boolean = false,

    val error: String? = null
) {
    val isPhoneNumberValid: Boolean
        get() = phoneNumber.isNotBlank() && phoneNumber.length >= 7

    val canContinue: Boolean
        get() = selectedCountry != null &&
                isPhoneNumberValid &&
                agreedToTerms
}