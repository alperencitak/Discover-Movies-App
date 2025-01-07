package com.alperencitak.discover_movies_app.view

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "movie_category_screen",
        modifier = Modifier.fillMaxSize()
    ) {
        composable("movie_category_screen") {
            MovieCategoryScreen(navController = navController)
        }
        composable("movie_detail_screen/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull() ?: 0
            MovieDetailScreen(movieId = movieId)
        }
    }
}