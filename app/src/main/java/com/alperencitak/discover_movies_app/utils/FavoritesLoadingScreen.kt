package com.alperencitak.discover_movies_app.utils

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alperencitak.discover_movies_app.ui.theme.SoftDarkBlue
import com.alperencitak.discover_movies_app.ui.theme.SoftRed
import kotlinx.coroutines.delay

@Composable
fun FavoritesLoadingScreen() {
    var iconColor by remember { mutableStateOf(Color.Yellow) }

    val animatedColor by animateColorAsState(
        targetValue = iconColor,
        animationSpec = tween(durationMillis = 1000)
    )

    val infiniteTransition = rememberInfiniteTransition(label = "scale animation")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "icon scale"
    )

    LaunchedEffect(Unit) {
        delay(1000L)
        iconColor = SoftRed
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(SoftDarkBlue)
    ){
        Icon(
            Icons.Default.Star,
            contentDescription = "favorites page intro Icon",
            tint = animatedColor,
            modifier = Modifier
                .size(48.dp)
                .scale(scale)
        )
    }
}