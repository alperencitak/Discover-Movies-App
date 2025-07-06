package com.alperencitak.discover_movies_app.presentation.search

import androidx.paging.PagingData
import com.alperencitak.discover_movies_app.domain.model.Movie
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val movies: Flow<PagingData<Movie>>? = null
)