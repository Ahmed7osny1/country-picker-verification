package com.example.countrypickerverification.presentation.screen.sign_up.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.countrypickerverification.domain.model.Country
import com.example.countrypickerverification.presentation.extensions.noRibbleClick
import com.example.countrypickerverification.presentation.screen.sign_up.SignupUiState

@Composable
fun PhoneNumberInput(
    selectedCountry: Country?,
    phoneNumber: String,
    isDropdownVisible: Boolean,
    uiState: SignupUiState,
    onCountrySelected: (Country) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    onCountryPickerClick: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.noRibbleClick { onCountryPickerClick() }
            ) {
                if (selectedCountry != null) {
                    FlagIcon(
                        country = selectedCountry,
                        modifier = Modifier.size(width = 28.dp, height = 20.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = selectedCountry.dialCode,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            BasicTextField(
                value = phoneNumber,
                onValueChange = onPhoneNumberChange,
                modifier = Modifier.weight(1f),
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                decorationBox = { innerTextField ->
                    if (phoneNumber.isEmpty()) {
                        Text(
                            text = "7xxxxxxxx",
                            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                        )
                    }
                    innerTextField()
                }
            )
        }

        if (isDropdownVisible) {
            CountryDropdownDialog(
                uiState = uiState,
                onCountrySelected = {
                    onCountrySelected(it)
                },
                onDismiss = onCountryPickerClick,
                onSearchQueryChanged = onSearchQueryChanged
            )
        }
    }
}