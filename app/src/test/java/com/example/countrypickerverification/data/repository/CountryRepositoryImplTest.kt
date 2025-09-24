package com.example.countrypickerverification.data.repository

import com.example.countrypickerverification.data.dto.CountryDto
import com.example.countrypickerverification.data.localData.CountryDummyData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@ExperimentalCoroutinesApi
class CountryRepositoryImplTest {

    private lateinit var countryDummyData: CountryDummyData
    private lateinit var repository: CountryRepositoryImpl

    @Before
    fun setup() {
        countryDummyData = mockk()
        repository = CountryRepositoryImpl(countryDummyData)
    }

    @Test
    fun `getCountries returns mapped list`() = runTest {
        coEvery { countryDummyData.getCountryList() } returns getDummyCountryList()

        val result = repository.getCountries()

        assertEquals(3, result.size)
        assertEquals("Egypt", result[0].name)
        assertEquals("LB", result[1].code)
    }

    @Test
    fun `getCountryByDialCode returns correct country`() = runTest {
        coEvery { countryDummyData.getCountryList() } returns getDummyCountryList()

        val result = repository.getCountryByDialCode("+20")

        assertNotNull(result)
        assertEquals("Egypt", result.name)
    }

    @Test
    fun `getCountryByDialCode returns null if not found`() = runTest {
        coEvery { countryDummyData.getCountryList() } returns emptyList()

        val result = repository.getCountryByDialCode("US")

        assertNull(result)
    }

    @Test
    fun `getCountryByCode returns null if not found`() = runTest {
        coEvery { countryDummyData.getCountryList() } returns emptyList()

        val result = repository.getCountryByCode("US")

        assertNull(result)
    }

    @Test
    fun `getCountryByCode handles mixed casing`() = runTest {
        coEvery { countryDummyData.getCountryList() } returns getDummyCountryList()

        val result = repository.getCountryByCode("eG")

        assertEquals("Egypt", result?.name)
    }

    @Test
    fun `searchCountries returns matching countries`() = runTest {
        coEvery { countryDummyData.getCountryList() } returns getDummyCountryList()

        val result = repository.searchCountries("leban")

        assertEquals(1, result.size)
        assertEquals("Lebanon", result[0].name)
    }

    private fun getDummyCountryList(): List<CountryDto> {
        return listOf(
            CountryDto("Egypt", "EG", "+20", ""),
            CountryDto("Lebanon", "LB", "+961", ""),
            CountryDto("United States", "US", "+1", "")
        )
    }
}