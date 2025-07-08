package com.alperencitak.discover_movies_app.presentation.favorites

import com.alperencitak.discover_movies_app.domain.model.Movie

data class FavoritesState (
    val movies: List<Movie> = emptyList()
)