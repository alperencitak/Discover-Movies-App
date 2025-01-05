package com.alperencitak.discover_movies_app.view

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.alperencitak.discover_movies_app.model.Movie
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.viewmodel.MovieViewModel
import kotlinx.coroutines.delay

@Composable
fun MovieListScreen() {
    val movieViewModel: MovieViewModel = hiltViewModel()
    val moviesByGenre by movieViewModel.moviesByGenre.collectAsState()
    val movies by movieViewModel.movies.collectAsState()
    val genres by movieViewModel.genres.collectAsState()
    var currentImageUrl by remember {
        mutableStateOf<String?>(null)
    }

    LaunchedEffect(movies) {
        if (movies.isNotEmpty() && currentImageUrl == null) {
            currentImageUrl = movies.take(15).shuffled().first().getFullPosterUrl()
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            currentImageUrl = movies.take(15).shuffled().first().getFullPosterUrl()
        }
    }

    if(movies.isNotEmpty()){
        movies.take(15).shuffled().first().getFullPosterUrl()
    }

    genres.forEach {
        println("${it.id} - ${it.name}")
    }

    movieViewModel.getMoviesByGenre(1, 27)
    movieViewModel.getMovies(1)

    Box(
        modifier = Modifier.fillMaxSize().background(SoftBlack)
    ){

        currentImageUrl?.let {
            Crossfade(targetState = it, animationSpec = tween(durationMillis = 1000)) { imageUrl ->
                HeadMovieItem(imageUrl)
            }
        }

        LazyRow {

        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f)
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(top = (LocalConfiguration.current.screenHeightDp / 2.5).dp)
        ) {
            items(moviesByGenre) { movie ->
                MovieItem(movie = movie, onClick = { println(movie.title) })
            }
        }
    }
}

@Composable
fun HeadMovieItem(imageUrl: String) {
    Box(
        modifier = Modifier
            .width(LocalConfiguration.current.screenWidthDp.dp)
            .height((LocalConfiguration.current.screenHeightDp / 2).dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Movie Poster",
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, SoftBlack),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = movie.getFullPosterUrl()),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Movie Poster",
            modifier = Modifier.fillMaxSize().height(300.dp)
        )
    }
}
