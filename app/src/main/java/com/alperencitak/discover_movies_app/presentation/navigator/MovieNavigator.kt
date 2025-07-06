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
import com.alperencitak.discover_movies_app.presentation.search.SearchScreen
import com.alperencitak.discover_movies_app.presentation.search.SearchViewModel

@Composable
fun MovieNavigator() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = "test",
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(
                route = "test"
            ) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent
                )
            }
        }
    }

}
