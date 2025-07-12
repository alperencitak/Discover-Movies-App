package com.alperencitak.discover_movies_app.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp

@Composable
fun getVoteColor(vote: Float): Color {
    val startColor = Color(0xFFFF5722)
    val endColor = Color(0xFF4CAF50)

    val fraction = (vote / 10).coerceIn(0.0f, 1.0f)
    return lerp(startColor, endColor, fraction)
}