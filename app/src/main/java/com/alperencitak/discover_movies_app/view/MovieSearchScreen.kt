package com.alperencitak.discover_movies_app.view

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.ui.theme.SoftRed
import com.alperencitak.discover_movies_app.utils.CircularLoadingScreen
import com.alperencitak.discover_movies_app.utils.HeadMovieItem
import com.alperencitak.discover_movies_app.utils.MainSearchBar
import com.alperencitak.discover_movies_app.utils.MovieItem
import com.alperencitak.discover_movies_app.utils.SearchedMovieItem
import com.alperencitak.discover_movies_app.viewmodel.MovieViewModel
import kotlinx.coroutines.delay

@Composable
fun MovieSearchScreen(navController: NavHostController) {
    val movieViewModel: MovieViewModel = hiltViewModel()
    val movies by movieViewModel.searchedMovies.collectAsState()
    var currentPage by remember { mutableIntStateOf(1) }
    var isLoadingMore by remember { mutableStateOf(false) }
    var localQuery by remember { mutableStateOf("") }

    LaunchedEffect(currentPage) {
        if(!isLoadingMore){
            isLoadingMore = true
            movieViewModel.searchMovie(localQuery, currentPage)
            isLoadingMore = false
        }
    }

    if (movies.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SoftBlack)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth().zIndex(2f),
                    horizontalArrangement = Arrangement.End
                ) {
                    MainSearchBar{ query ->
                        currentPage = 1
                        localQuery = query
                        movieViewModel.searchMovie(query, 1)
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(top = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val movieList = movies.filter { it.poster_path != null }
                    items(movieList) { movie ->
                        SearchedMovieItem(movie = movie) {
                            navController.navigate("movie_detail/${movie.id}")
                        }
                    }
                    item{
                        if(!isLoadingMore){
                            LaunchedEffect(Unit) {
                                currentPage++
                            }
                        }
                    }
                }
            }
        }
    } else {
        CircularLoadingScreen()
    }
}
