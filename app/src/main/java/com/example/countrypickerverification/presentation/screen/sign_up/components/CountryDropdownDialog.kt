package com.example.countrypickerverification.presentation.screen.sign_up.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.countrypickerverification.R
import com.example.countrypickerverification.domain.model.Country
import com.example.countrypickerverification.presentation.screen.sign_up.SignupUiState

@Composable
fun CountryDropdownDialog(
    uiState: SignupUiState,
    onCountrySelected: (Country) -> Unit,
    onDismiss: () -> Unit,
    onSearchQueryChanged: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(searchQuery.text) {
        onSearchQueryChanged(searchQuery.text)
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.select_country),
                    style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black
                    ), modifier = Modifier.padding(bottom = 16.dp)
                )

                SearchField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                when {
                    uiState.isLoadingCountries -> {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = Color(0xFF6366F1))
                        }
                    }

                    uiState.filteredCountries.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.no_countries_found),
                                style = TextStyle(
                                    fontSize = 16.sp, color = Color.Gray
                                )
                            )
                        }
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            items(
                                uiState.filteredCountries
                            ) { country ->
                                CountryItem(
                                    country = country,
                                    isSelected = country.code == uiState.selectedCountry?.code,
                                    onClick = { onCountrySelected(country) })
                            }
                        }
                    }
                }
            }
        }
    }
}
