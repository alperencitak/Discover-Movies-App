package com.alperencitak.discover_movies_app.data.remote.dto

import com.alperencitak.discover_movies_app.domain.model.Movie

data class MovieListResponse(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)
