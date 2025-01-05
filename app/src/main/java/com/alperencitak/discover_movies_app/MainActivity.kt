package com.alperencitak.discover_movies_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alperencitak.discover_movies_app.ui.theme.DiscoverMoviesAppTheme
import com.alperencitak.discover_movies_app.view.MovieCategoryScreen
import com.alperencitak.discover_movies_app.view.MovieListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiscoverMoviesAppTheme {
                //MovieListScreen()
                MovieCategoryScreen()
            }
        }
    }
}