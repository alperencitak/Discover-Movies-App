package com.alperencitak.discover_movies_app.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


private val DarkColorScheme = darkColorScheme(
    primary = CinematicRed,
    onPrimary = CinematicWhite,
    secondary = CinematicLightGray,
    onSecondary = CinematicWhite,
    background = CinematicBlack,
    onBackground = CinematicWhite,
    surface = CinematicDarkGray,
    onSurface = CinematicWhite,
    tertiary = CinematicRed,
    onTertiary = CinematicWhite
)

private val LightColorScheme = lightColorScheme(
    primary = CinematicRed,
    onPrimary = CinematicWhite,
    secondary = CinematicLightGray,
    onSecondary = CinematicWhite,
    background = CinematicBlack,
    onBackground = CinematicWhite,
    surface = CinematicDarkGray,
    onSurface = CinematicWhite,
    tertiary = CinematicRed,
    onTertiary = CinematicWhite
)


@Composable
fun MyAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes(),
        content = content
    )
}

@Composable
fun DiscoverMoviesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}