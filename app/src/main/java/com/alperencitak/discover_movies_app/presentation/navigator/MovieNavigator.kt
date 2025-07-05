package com.alperencitak.discover_movies_app.presentation.navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.alperencitak.discover_movies_app.presentation.home.HomeScreen
import com.alperencitak.discover_movies_app.presentation.home.HomeViewModel

@Composable
fun MovieNavigator() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(
                route = "home"
            ) {
                val viewModel: HomeViewModel = hiltViewModel()
                val movies = viewModel.movies.collectAsLazyPagingItems()
                HomeScreen(
                    movies = movies
                )
            }
        }
    }

}
