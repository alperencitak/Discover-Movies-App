package com.alperencitak.discover_movies_app.model

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)
