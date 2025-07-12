package com.alperencitak.discover_movies_app.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.ui.theme.SoftDarkBlue
import com.alperencitak.discover_movies_app.ui.theme.SoftRed

@Composable
fun CircularLoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(SoftDarkBlue)
    ){
        CircularProgressIndicator(
            color = SoftRed,
            strokeWidth = 4.dp
        )
    }
}