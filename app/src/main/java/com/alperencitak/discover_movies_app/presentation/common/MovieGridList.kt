package com.alperencitak.discover_movies_app.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.presentation.home.MovieGridCard

@Composable
fun MovieGridList(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<Movie>,
    gridCells: Int = 3
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(gridCells),
        modifier = modifier
            .padding(horizontal = 12.dp),
        contentPadding = PaddingValues(bottom = 80.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(count = movies.itemCount){
            movies[it]?.let {
                MovieGridCard(movie = it, onClick = { })
            }
        }
    }
}