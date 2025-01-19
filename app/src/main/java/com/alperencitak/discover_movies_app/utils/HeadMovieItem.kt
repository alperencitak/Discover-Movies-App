package com.alperencitak.discover_movies_app.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack

@Composable
fun HeadMovieItem(imageUrl: String) {
    Box(
        modifier = Modifier
            .width(LocalConfiguration.current.screenWidthDp.dp)
            .height((LocalConfiguration.current.screenHeightDp / 2).dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Movie Poster",
            modifier = Modifier.fillMaxSize().background(SoftBlack)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, SoftBlack),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )
    }
}