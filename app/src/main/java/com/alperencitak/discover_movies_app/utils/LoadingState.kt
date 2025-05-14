package com.alperencitak.discover_movies_app.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alperencitak.discover_movies_app.ui.theme.SoftRed

@Composable
fun LoadingState(nunito: FontFamily) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CircularProgressIndicator(
                color = SoftRed,
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = "Loading your favorites...",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontFamily = nunito,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}