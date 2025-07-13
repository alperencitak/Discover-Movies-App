package com.alperencitak.discover_movies_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alperencitak.discover_movies_app.presentation.navigator.MovieNavigator
import com.alperencitak.discover_movies_app.presentation.navigator.Route
import com.alperencitak.discover_movies_app.ui.theme.CinematicBlack
import com.alperencitak.discover_movies_app.ui.theme.DiscoverMoviesAppTheme
import com.alperencitak.discover_movies_app.ui.theme.MyAppTheme
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.ui.theme.SoftDarkBlue
import com.alperencitak.discover_movies_app.ui.theme.SoftRed
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyAppTheme {
                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()
                var statusBarColor = remember { mutableStateOf(Color(0xFF1F1D2B)) }

                SideEffect {
                    systemController.setStatusBarColor(
                        color = statusBarColor.value, // ya da direkt Color(0xFF1F1D2B)
                        darkIcons = !isSystemInDarkMode
                    )
                    systemController.setNavigationBarColor(
                        color = CinematicBlack,
                        darkIcons = !isSystemInDarkMode
                    )
                }

                Box(
                    modifier = Modifier.background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                statusBarColor.value,
                                Color(0xFF171621)
                            )
                        )
                    )
                ) {
                    MovieNavigator(
                        statusBarChange = { route ->
                            statusBarColor.value = if (
                                route == Route.CategoriesScreen.route
                            ) {
                                CinematicBlack
                            }else if(
                                route == Route.SearchScreen.route
                            ) {
                                SoftBlack
                            }else {
                                Color(0xFF1F1D2B)
                            }
                        }
                    )
                }
            }
        }
    }
}
