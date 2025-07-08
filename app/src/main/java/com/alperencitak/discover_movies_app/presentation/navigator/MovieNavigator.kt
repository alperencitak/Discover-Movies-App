package com.alperencitak.discover_movies_app.presentation.navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.alperencitak.discover_movies_app.data.remote.dto.MovieResponse
import com.alperencitak.discover_movies_app.domain.model.Genre
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.domain.model.Video
import com.alperencitak.discover_movies_app.presentation.details.DetailsEvent
import com.alperencitak.discover_movies_app.presentation.details.DetailsScreen
import com.alperencitak.discover_movies_app.presentation.details.DetailsViewModel
import com.alperencitak.discover_movies_app.presentation.favorites.FavoritesScreen
import com.alperencitak.discover_movies_app.presentation.favorites.FavoritesViewModel
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
                val viewModel: DetailsViewModel = hiltViewModel()
                val movieResponse = viewModel.movieResponse
                val isFavorite = viewModel.isFavorite

                val id = 986056

                LaunchedEffect(key1 = id) {
                    viewModel.fetchMovie(id)
                    viewModel.onEvent(DetailsEvent.CheckIfFavorite(id))
                }

                val youtubeVideo = remember(movieResponse) {
                    movieResponse?.videos?.find { it.site == "YouTube" && it.type == "Trailer" }
                }

                val movie = movieResponse?.movie
                val casts = movieResponse?.casts
                val crews = movieResponse?.crews

                DetailsScreen(
                    movie = movie,
                    trailer = youtubeVideo,
                    casts = casts,
                    crews = crews,
                    event = viewModel::onEvent,
                    isFavorite = isFavorite
                )
            }
        }
    }

}
