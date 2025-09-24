package com.example.countrypickerverification.presentation.screen.sign_up

import com.example.countrypickerverification.domain.model.Country
import com.example.countrypickerverification.domain.repository.CountryRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

@ExperimentalCoroutinesApi
class SignupViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var repository: CountryRepository
    private lateinit var viewModel: SignupViewModel

    private val dummyCountries = listOf(
        Country("Egypt", "EG", "+20", ""),
        Country("Lebanon", "LB", "+961", ""),
        Country("United States", "US", "+1", "")
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockkStatic(Dispatchers::class)
        repository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init loads countries and selects default`() = runTest {
        coEvery { repository.getCountries() } returns dummyCountries
        coEvery { repository.getCountryByCode("US") } returns dummyCountries[2]

        viewModel = SignupViewModel(repository)
        advanceUntilIdle()

        val state = viewModel.uiState.value

        assertThat(state.countries).isEqualTo(dummyCountries)
        assertThat(state.selectedCountry).isEqualTo(dummyCountries[2])
        assertThat(state.isLoadingCountries).isFalse()
    }

    @Test
    fun `init selects first country if default not found`() = runTest {
        coEvery { repository.getCountries() } returns dummyCountries
        coEvery { repository.getCountryByCode("US") } returns null

        viewModel = SignupViewModel(repository)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(
            dummyCountries.first(), state.selectedCountry
        )

    }

    @Test
    fun `searchCountries with blank query restores full list`() = runTest {
        coEvery { repository.getCountries() } returns dummyCountries
        coEvery { repository.getCountryByCode("US") } returns dummyCountries[2]

        viewModel = SignupViewModel(repository)
        viewModel.searchCountries("")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(dummyCountries, state.filteredCountries)

    }

    @Test
    fun `searchCountries with query filters list`() = runTest {
        coEvery { repository.getCountries() } returns dummyCountries
        coEvery { repository.getCountryByCode("US") } returns dummyCountries[2]
        coEvery { repository.searchCountries("leban") } returns listOf(dummyCountries[1])

        viewModel = SignupViewModel(repository)
        viewModel.searchCountries("leban")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(listOf(dummyCountries[1]), state.filteredCountries)
        assertEquals("leban", state.searchQuery)
    }

    @Test
    fun `searchCountries handles exception`() = runTest {
        coEvery { repository.getCountries() } returns dummyCountries
        coEvery { repository.getCountryByCode("US") } returns dummyCountries[2]
        coEvery { repository.searchCountries("fail") } throws RuntimeException("Search failed")

        viewModel = SignupViewModel(repository)
        viewModel.searchCountries("fail")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals("Search failed", state.error)
    }

    @Test
    fun `selectCountry updates selectedCountry`() = runTest {
        coEvery { repository.getCountries() } returns dummyCountries
        coEvery { repository.getCountryByCode("US") } returns dummyCountries[2]

        viewModel = SignupViewModel(repository)
        viewModel.selectCountry(dummyCountries[0])
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(dummyCountries[0], state.selectedCountry)

    }

    @Test
    fun `showCountryDropdown sets isDropdownVisible true`() = runTest {
        coEvery { repository.getCountries() } returns dummyCountries
        coEvery { repository.getCountryByCode("US") } returns dummyCountries[2]

        viewModel = SignupViewModel(repository)
        viewModel.showCountryDropdown()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state.isDropdownVisible)

    }

    @Test
    fun `hideCountryDropdown resets search and visibility`() = runTest {
        coEvery { repository.getCountries() } returns dummyCountries
        coEvery { repository.getCountryByCode("US") } returns dummyCountries[2]

        viewModel = SignupViewModel(repository)
        viewModel.searchCountries("eg")
        viewModel.hideCountryDropdown()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isDropdownVisible)
        assertEquals("", state.searchQuery)
        assertEquals(dummyCountries, state.filteredCountries)

    }

    @Test
    fun `updatePhoneNumber sets phoneNumber`() = runTest {
        coEvery { repository.getCountries() } returns dummyCountries
        coEvery { repository.getCountryByCode("US") } returns dummyCountries[2]

        viewModel = SignupViewModel(repository)
        viewModel.updatePhoneNumber("123456")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals("123456", state.phoneNumber)

    }

    @Test
    fun `updateTermsAgreement sets agreedToTerms`() = runTest {
        coEvery { repository.getCountries() } returns dummyCountries
        coEvery { repository.getCountryByCode("US") } returns dummyCountries[2]

        viewModel = SignupViewModel(repository)
        viewModel.updateTermsAgreement(true)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state.agreedToTerms)

    }

    @Test
    fun `onContinueClicked with valid phone sets isLoading false`() = runTest {
        coEvery { repository.getCountries() } returns dummyCountries
        coEvery { repository.getCountryByCode("US") } returns dummyCountries[2]

        viewModel = SignupViewModel(repository)
        viewModel.updatePhoneNumber("1234567")

        viewModel.onContinueClicked()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
    }

    @Test
    fun `onContinueClicked with invalid phone sets error`() = runTest {
        coEvery { repository.getCountries() } returns dummyCountries
        coEvery { repository.getCountryByCode("US") } returns dummyCountries[2]

        viewModel = SignupViewModel(repository)
        viewModel.updatePhoneNumber("")

        viewModel.onContinueClicked()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals("Please enter a valid phone number", state.error)

    }
}