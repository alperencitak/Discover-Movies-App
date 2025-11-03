package com.alperencitak.discover_movies_app.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alperencitak.discover_movies_app.domain.model.Cast
import com.alperencitak.discover_movies_app.domain.model.Crew
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.domain.model.Video
import com.alperencitak.discover_movies_app.presentation.details.components.MovieInformationField
import com.alperencitak.discover_movies_app.presentation.details.components.MovieTrailerAndPoster
import com.alperencitak.discover_movies_app.ui.theme.SoftDarkBlue

@Composable
fun DetailsScreen(
    movie: Movie?,
    trailer: Video?,
    casts: List<Cast>?,
    crews: List<Crew>?,
    event: (DetailsEvent) -> Unit,
    isFavorite: Boolean,
    navigateUp: () -> Unit,
    navigateToCastMovies: (Cast) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftDarkBlue)
            .verticalScroll(rememberScrollState())
    ) {
        MovieTrailerAndPoster(
            movie = movie,
            trailer = trailer,
            onFavoriteClick = {
                if(movie != null){
                    event(DetailsEvent.UpsertDeleteMovie(movie))
                }
            },
            isFavorite=isFavorite,
            navigateUp={ navigateUp() }
        )
        MovieInformationField(
            movie = movie,
            casts = casts,
            crews = crews,
            onClickCast = { navigateToCastMovies(it) }
        )
    }
}