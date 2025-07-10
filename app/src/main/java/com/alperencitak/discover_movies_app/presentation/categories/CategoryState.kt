package com.alperencitak.discover_movies_app.presentation.categories

import androidx.paging.PagingData
import com.alperencitak.discover_movies_app.domain.model.Genre
import com.alperencitak.discover_movies_app.domain.model.Movie
import kotlinx.coroutines.flow.Flow

data class CategoryState(
    val genreId: Int = 28,
    val allGenres: List<Genre> = emptyList(),
    val movies: Flow<PagingData<Movie>>? = null
)