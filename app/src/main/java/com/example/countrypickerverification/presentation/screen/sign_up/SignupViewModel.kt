package com.example.countrypickerverification.presentation.screen.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrypickerverification.domain.model.Country
import com.example.countrypickerverification.domain.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignupViewModel(
    private val repository: CountryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignupUiState())
    val uiState: StateFlow<SignupUiState> = _uiState.asStateFlow()

    init {
        loadCountriesAndDefault()
    }

    private fun loadCountriesAndDefault() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoadingCountries = true, error = null)

            val countries = repository.getCountries()
            val defaultCountry = repository.getCountryByCode("US")
                ?: countries.firstOrNull()

            _uiState.value = _uiState.value.copy(
                isLoadingCountries = false,
                countries = countries,
                filteredCountries = countries,
                selectedCountry = defaultCountry
            )
        }
    }

    fun searchCountries(query: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(searchQuery = query)
            try {
                val filteredCountries = if (query.isBlank()) {
                    _uiState.value.countries
                } else {
                    repository.searchCountries(query)
                }
                _uiState.value = _uiState.value.copy(filteredCountries = filteredCountries)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Search failed"
                )
            }
        }
    }

    fun selectCountry(country: Country) {
        _uiState.value = _uiState.value.copy(selectedCountry = country)
    }

    fun showCountryDropdown() {
        _uiState.value = _uiState.value.copy(isDropdownVisible = true)
    }

    fun hideCountryDropdown() {
        _uiState.value = _uiState.value.copy(
            isDropdownVisible = false,
            searchQuery = "",
            filteredCountries = _uiState.value.countries
        )
    }

    fun updatePhoneNumber(number: String) {
        _uiState.value = _uiState.value.copy(phoneNumber = number)
    }

    fun updateTermsAgreement(agreed: Boolean) {
        _uiState.value = _uiState.value.copy(agreedToTerms = agreed)
    }

    fun onContinueClicked() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            if (_uiState.value.isPhoneNumberValid) {
                _uiState.value = _uiState.value.copy(isLoading = false)
            } else {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Please enter a valid phone number"
                )
            }
        }
    }
}