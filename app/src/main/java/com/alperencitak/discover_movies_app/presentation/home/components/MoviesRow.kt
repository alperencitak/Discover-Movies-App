package com.alperencitak.discover_movies_app.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.presentation.common.MovieGridCard

@Composable
fun MoviesRow(
    movies: LazyPagingItems<Movie>,
    navigateToDetails: (Movie) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(count = movies.itemCount) {
            movies[it]?.let { movie ->
                MovieGridCard(movie = movie, onClick = { navigateToDetails(movie) })
            }
        }
    }
}
