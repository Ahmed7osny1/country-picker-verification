package com.example.countrypickerverification.presentation.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier

fun Modifier.noRibbleClick(
    onClick: () -> Unit,
): Modifier = this.clickable(
    interactionSource = MutableInteractionSource(),
    indication = null,
    onClick = onClick
)