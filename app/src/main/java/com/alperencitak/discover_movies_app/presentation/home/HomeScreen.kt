package com.alperencitak.discover_movies_app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.presentation.common.MovieGridCard
import com.alperencitak.discover_movies_app.presentation.home.components.MoviesRow

@Composable
fun HomeScreen(
    movies: LazyPagingItems<Movie>,
    topRatedMovies: LazyPagingItems<Movie>,
    navigateToDetails: (Movie) -> Unit
) {

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
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            contentPadding = PaddingValues(top = 12.dp, bottom = 80.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item(span = { GridItemSpan(3) }) {
                Text(
                    text = stringResource(R.string.top_rated),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp)
                )
            }

            item(span = { GridItemSpan(3) }) {
                MoviesRow(
                    movies = topRatedMovies,
                    navigateToDetails = { navigateToDetails(it) }
                )
            }

            item(span = { GridItemSpan(3) }) {
                Text(
                    text = stringResource(R.string.all_movies),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp)
                )
            }

            items(count = movies.itemCount) {
                movies[it]?.let { movie ->
                    MovieGridCard(movie = movie, onClick = { navigateToDetails(movie) })
                }
            }

        }
    }

}
