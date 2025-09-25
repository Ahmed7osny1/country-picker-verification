package com.example.countrypickerverification.presentation.screen.sign_up

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.countrypickerverification.R
import com.example.countrypickerverification.domain.model.Country
import com.example.countrypickerverification.presentation.screen.sign_up.components.HeaderContent
import com.example.countrypickerverification.presentation.screen.sign_up.components.PhoneNumberInput
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    viewModel: SignupViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    SignUpContent(
        uiState = uiState,
        onCountrySelected = {
            viewModel.selectCountry(it)
            viewModel.hideCountryDropdown()
        },
        onPhoneNumberChange = viewModel::updatePhoneNumber,
        onCountryPickerClick = {
            if (uiState.isDropdownVisible) {
                viewModel.hideCountryDropdown()
            } else {
                viewModel.showCountryDropdown()
            }
        },
        onSearchQueryChanged = viewModel::searchCountries,
        onCheckedChange = viewModel::updateTermsAgreement,
        onButtonClicked = viewModel::onContinueClicked
    )
}

@Composable
private fun SignUpContent(
    uiState: SignupUiState,
    onCountrySelected: (Country) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    onCountryPickerClick: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    onButtonClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
            .systemBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.one_last_step),
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        HeaderContent()

        Spacer(modifier = Modifier.height(40.dp))

        PhoneNumberInput(
            selectedCountry = uiState.selectedCountry,
            phoneNumber = uiState.phoneNumber,
            isDropdownVisible = uiState.isDropdownVisible,
            uiState = uiState,
            onCountrySelected = onCountrySelected,
            onPhoneNumberChange = onPhoneNumberChange,
            onCountryPickerClick = onCountryPickerClick,
            onSearchQueryChanged = onSearchQueryChanged,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.verify_your_account),
            style = TextStyle(fontSize = 12.sp, color = Color.Gray),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = uiState.agreedToTerms,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF6366F1))
            )

            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black
                        )
                    ) {
                        append(stringResource(R.string.guidlines_text))
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF4B8CF6),
                        )
                    ) {
                        append(stringResource(R.string.guidelines_sub_text))
                    }
                },
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        val context = LocalContext.current
        Button(
            onClick = {
                Toast.makeText(context, "check your verify code", Toast.LENGTH_LONG).show()
                onButtonClicked()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6366F1)),
            enabled = uiState.canContinue
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Continue",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_forward),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(50))
                )
            }
        }
    }
}