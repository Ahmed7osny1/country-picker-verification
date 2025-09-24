package com.example.countrypickerverification.presentation.screen.sign_up.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderContent() {
    Text(
        text = "Please login or signup for a",
        style = TextStyle(
            fontSize = 18.sp,
            color = Color.Black
        ),
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            ) {
                append("free")
            }
            withStyle(
                style = SpanStyle(
                    color = Color.Black,
                )
            ) {
                append(" account to continue")
            }
        },
        fontSize = 18.sp
    )
}

@Preview
@Composable
private fun HeaderContentPreview() {
    HeaderContent()
}