package com.alperencitak.discover_movies_app.presentation.castmovies

import androidx.paging.PagingData
import com.alperencitak.discover_movies_app.domain.model.Genre
import com.alperencitak.discover_movies_app.domain.model.Movie
import kotlinx.coroutines.flow.Flow

data class CastMoviesState(
    val castId: Int = 0,
    val movies: Flow<PagingData<Movie>>? = null
)