package com.example.countrypickerverification.presentation.screen.sign_up.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.countrypickerverification.domain.model.Country

@Composable
fun CountryItem(
    country: Country,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFFF3F4F6) else Color.Transparent

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor, shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        FlagIcon(
            country = country, modifier = Modifier.size(size = 24.dp)
        )

        Spacer(modifier = Modifier.width(width = 12.dp))

        Column(
            modifier = Modifier.weight(weight = 1f)
        ) {
            Text(
                text = country.name,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            )
        }
        Text(
            text = country.dialCode,
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Black
            )
        )
    }
}

@Preview
@Composable
private fun CountryItemPreview() {
    CountryItem(
        country = Country(
            name = "Egypt",
            code = "EG",
            dialCode = "+20",
            flagUrl = "https://flagcdn.com/w40/eg.png"
        ),
        isSelected = true,
        onClick = { }
    )
}