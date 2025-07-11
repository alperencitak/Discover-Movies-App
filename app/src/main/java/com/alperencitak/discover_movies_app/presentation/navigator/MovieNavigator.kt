package com.alperencitak.discover_movies_app.presentation.navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.data.remote.dto.MovieResponse
import com.alperencitak.discover_movies_app.domain.model.Genre
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.domain.model.Video
import com.alperencitak.discover_movies_app.presentation.categories.CategoriesScreen
import com.alperencitak.discover_movies_app.presentation.categories.CategoriesViewModel
import com.alperencitak.discover_movies_app.presentation.details.DetailsEvent
import com.alperencitak.discover_movies_app.presentation.details.DetailsScreen
import com.alperencitak.discover_movies_app.presentation.details.DetailsViewModel
import com.alperencitak.discover_movies_app.presentation.favorites.FavoritesScreen
import com.alperencitak.discover_movies_app.presentation.favorites.FavoritesViewModel
import com.alperencitak.discover_movies_app.presentation.home.HomeScreen
import com.alperencitak.discover_movies_app.presentation.home.HomeViewModel
import com.alperencitak.discover_movies_app.presentation.navigator.components.CustomBottomNavigation
import com.alperencitak.discover_movies_app.presentation.navigator.components.CustomNavigationItem
import com.alperencitak.discover_movies_app.presentation.search.SearchScreen
import com.alperencitak.discover_movies_app.presentation.search.SearchViewModel

@Composable
fun MovieNavigator() {
    val navItems = remember {
        listOf(
            CustomNavigationItem(
                icon = R.drawable.home,
                text = "Home"
            ),
            CustomNavigationItem(
                icon = R.drawable.search,
                text = "Search"
            ),
            CustomNavigationItem(
                icon = R.drawable.category,
                text = "Categories"
            ),
            CustomNavigationItem(
                icon = R.drawable.favorite,
                text = "Favorites"
            )
        )
    }

    val navController = rememberNavController()
    var backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    val isBottomBarVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route == Route.HomeScreen.route ||
                backstackState?.destination?.route == Route.SearchScreen.route ||
                backstackState?.destination?.route == Route.CategoriesScreen.route ||
                backstackState?.destination?.route == Route.FavoritesScreen.route
    }

    selectedItem = remember(key1 = backstackState) {
        when (backstackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.CategoriesScreen.route -> 2
            Route.FavoritesScreen.route -> 3
            else -> 0
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        bottomBar = {
            CustomBottomNavigation(
                items = navItems,
                selected = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> {
                            navigateToTap(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )
                        }

                        1 -> {
                            navigateToTap(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )
                        }

                        2 -> {
                            navigateToTap(
                                navController = navController,
                                route = Route.CategoriesScreen.route
                            )
                        }

                        3 -> {
                            navigateToTap(
                                navController = navController,
                                route = Route.FavoritesScreen.route
                            )
                        }
                    }
                }
            )
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(
                route = Route.HomeScreen.route
            ) {
                val viewModel: HomeViewModel = hiltViewModel()
                val movies = viewModel.movies.collectAsLazyPagingItems()
                val topRatedMovies = viewModel.topRatedMovies.collectAsLazyPagingItems()
                HomeScreen(
                    movies = movies,
                    topRatedMovies = topRatedMovies,
                    navigateToDetails = { movie ->
                        navigateToDetails(
                            navController = navController,
                            movie = movie
                        )
                    }
                )
            }
            composable(
                route = Route.SearchScreen.route
            ) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { movie ->
                        navigateToDetails(
                            navController = navController,
                            movie = movie
                        )
                    }
                )
            }
            composable(
                route = Route.CategoriesScreen.route
            ) {
                val viewModel: CategoriesViewModel = hiltViewModel()
                val genres = viewModel.state.value.allGenres
                if (genres.isNotEmpty()) {
                    CategoriesScreen(
                        state = viewModel.state.value,
                        event = viewModel::onEvent,
                        categories = genres,
                        navigateToDetails = { movie ->
                            navigateToDetails(
                                navController = navController,
                                movie = movie
                            )
                        }
                    )
                }
            }
            composable(
                route = Route.FavoritesScreen.route
            ) {
                val viewModel: FavoritesViewModel = hiltViewModel()
                val state = viewModel.state.value
                FavoritesScreen(
                    state = state,
                    navigateToDetails = { movie ->
                        navigateToDetails(
                            navController = navController,
                            movie = movie
                        )
                    }
                )
            }
            composable(
                route = "details"
            ) {
                val viewModel: DetailsViewModel = hiltViewModel()
                val movieResponse = viewModel.movieResponse
                val isFavorite = viewModel.isFavorite

                navController.previousBackStackEntry?.savedStateHandle?.get<Movie?>("movie")
                    ?.let { movieLocal ->
                        val id = movieLocal.id
                        LaunchedEffect(key1 = id) {
                            viewModel.fetchMovie(id)
                            viewModel.onEvent(DetailsEvent.CheckIfFavorite(id))
                        }

                        val youtubeVideo = remember(movieResponse) {
                            movieResponse?.videos?.find { video -> video.site == "YouTube" && video.type == "Trailer" }
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
                            isFavorite = isFavorite,
                            navigateUp = {
                                navController.navigateUp()
                            }
                        )
                    }
            }
        }
    }

}

private fun navigateToTap(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController, movie: Movie) {
    navController.currentBackStackEntry?.savedStateHandle?.set("movie", movie)
    navController.navigate(Route.DetailsScreen.route)
}
