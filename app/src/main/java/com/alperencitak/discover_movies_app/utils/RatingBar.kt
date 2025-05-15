package com.alperencitak.discover_movies_app.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(rating: Float) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        repeat(5) { index ->
            val starProgress = when {
                rating >= (index + 1) * 2 -> 1f
                rating > index * 2 -> (rating - (index * 2)) / 2
                else -> 0f
            }

            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = lerp(
                    Color.White.copy(alpha = 0.3f),
                    Color(0xFFFFC107),
                    starProgress
                ),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}