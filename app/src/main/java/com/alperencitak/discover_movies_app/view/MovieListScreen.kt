package com.alperencitak.discover_movies_app.view

//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.GridItemSpan
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavHostController
//import androidx.paging.LoadState
//import androidx.paging.compose.collectAsLazyPagingItems
//import com.alperencitak.discover_movies_app.ui.theme.SoftRed
//import com.alperencitak.discover_movies_app.utils.LoadingIndicator
//import com.alperencitak.discover_movies_app.utils.MovieGridCard
//import com.alperencitak.discover_movies_app.utils.TrendingMovieCard
//import com.alperencitak.discover_movies_app.viewmodel.MovieViewModel
//
//@Composable
//fun MovieListScreen(navController: NavHostController) {
//    val movieViewModel: MovieViewModel = hiltViewModel()
//    val trendingMoviesToday by movieViewModel.trendingMovies.collectAsState()
//    val pagedMovies = movieViewModel.pagedMovies.collectAsLazyPagingItems()
//
//    LaunchedEffect(Unit) {
//        movieViewModel.getTrendingMovies(1, "day")
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(
//                brush = Brush.verticalGradient(
//                    colors = listOf(
//                        Color(0xFF1F1D2B),
//                        Color(0xFF171621)
//                    )
//                )
//            )
//    ) {
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(3),
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 12.dp),
//            contentPadding = PaddingValues(top = 32.dp, bottom = 80.dp),
//            horizontalArrangement = Arrangement.spacedBy(12.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            // Trending Movies
//            if (trendingMoviesToday.isNotEmpty()) {
//                item(span = { GridItemSpan(3) }) {
//                    Column{
//                        Text(
//                            text = "Trending Movies",
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 24.sp,
//                            color = Color.White,
//                            modifier = Modifier.padding(vertical = 16.dp)
//                        )
//
//                        LazyRow(
//                            horizontalArrangement = Arrangement.spacedBy(12.dp),
//                            contentPadding = PaddingValues(bottom = 16.dp)
//                        ) {
//                            items(trendingMoviesToday) { movie ->
//                                TrendingMovieCard(movie) {
//                                    navController.navigate("movie_detail/${movie.id}")
//                                }
//                            }
//                        }
//                    }
//                }
//                item(span = { GridItemSpan(3) }) {
//                    Text(
//                        text = "All Movies",
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 24.sp,
//                        color = Color.White,
//                        modifier = Modifier.padding(vertical = 16.dp)
//                    )
//                }
//            }
//
//            // All Movies
//            items(pagedMovies.itemCount) { index ->
//                pagedMovies[index]?.let { movie ->
//                    MovieGridCard(movie) {
//                        navController.navigate("movie_detail/${movie.id}")
//                    }
//                }
//            }
//
//            // Loading
//            when (pagedMovies.loadState.append) {
//                is LoadState.Loading -> {
//                    item(span = { GridItemSpan(3) }) {
//                        LoadingIndicator()
//                    }
//                }
//                is LoadState.Error -> {
//                    item(span = { GridItemSpan(3) }) {
//                        ErrorMessage(
//                            onRetry = { pagedMovies.retry() }
//                        )
//                    }
//                }
//                else -> {}
//            }
//        }
//    }
//}
//
//@Composable
//fun ErrorMessage(onRetry: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Couldn't load movies",
//            style = MaterialTheme.typography.bodyLarge.copy(
//                color = Color.White.copy(alpha = 0.7f)
//            )
//        )
//        TextButton(
//            onClick = onRetry,
//            colors = ButtonDefaults.textButtonColors(
//                contentColor = SoftRed
//            )
//        ) {
//            Text("Retry")
//        }
//    }
//}
