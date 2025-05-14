package com.alperencitak.discover_movies_app.view

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.ui.theme.SoftRed
import com.alperencitak.discover_movies_app.utils.EmptyFavoritesState
import com.alperencitak.discover_movies_app.utils.LoadingState
import com.alperencitak.discover_movies_app.utils.MovieGridCard
import com.alperencitak.discover_movies_app.viewmodel.MovieViewModel
import com.alperencitak.discover_movies_app.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteMoviesScreen(navController: NavHostController) {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val movieViewModel: MovieViewModel = hiltViewModel()
    val favoriteIds by profileViewModel.favorites.collectAsState()
    val movies by movieViewModel.favoriteMovies.collectAsState()
    val nunito = FontFamily(Font(R.font.nunito_black))
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(favoriteIds) {
        movieViewModel.getFavoriteMovies(favoriteIds)
    }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(1500)
        isLoading = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1F1D2B),
                        Color(0xFF171621)
                    )
                )
            )
    ) {
        AnimatedVisibility(
            visible = isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            LoadingState(nunito)
        }

        AnimatedVisibility(
            visible = !isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            if (movies.isEmpty()) {
                EmptyFavoritesState(
                    nunito = nunito,
                    onExploreClick = { navController.navigate("movie_list") }
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 24.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    item(span = { GridItemSpan(2) }) {
                        Column(
                            modifier = Modifier.padding(top = 32.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Back",
                                    tint = SoftRed
                                )
                                Text(
                                    text = "Favorites",
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontFamily = nunito,
                                        fontWeight = FontWeight.Bold,
                                        color = SoftRed,
                                        fontSize = 24.sp
                                    )
                                )
                            }
                            Text(
                                text = "${movies.size} ${if (movies.size == 1) "Movie" else "Movies"}",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontFamily = nunito,
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }

                    items(movies.size) { index ->
                        MovieGridCard(movie = movies[index]) {
                            navController.navigate("movie_detail/${movies[index].id}")
                        }
                    }
                }
            }
        }
    }
}