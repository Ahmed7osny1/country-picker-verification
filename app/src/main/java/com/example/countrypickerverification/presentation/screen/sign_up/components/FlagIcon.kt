package com.example.countrypickerverification.presentation.screen.sign_up.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.countrypickerverification.domain.model.Country

@Composable
fun FlagIcon(
    country: Country,
    modifier: Modifier = Modifier
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(country.flagUrl)
            .crossfade(true)
            .size(size = 64)
            .build()
    )

    Box(
        modifier = modifier.clip(RoundedCornerShape(2.dp))
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF0F0F0), RoundedCornerShape(2.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(12.dp),
                        strokeWidth = 1.dp,
                        color = Color(0xFF6366F1)
                    )
                }
            }

            is AsyncImagePainter.State.Error -> {
                CountryFlagError(
                    country = country,
                    modifier = Modifier.matchParentSize()
                )
            }

            else -> {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun CountryFlagError(
    country: Country,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color(0xFF4CAF50), RoundedCornerShape(2.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = country.code.uppercase(),
            style = TextStyle(
                fontSize = 8.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}