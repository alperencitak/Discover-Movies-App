package com.alperencitak.discover_movies_app.repository

import com.alperencitak.discover_movies_app.api.TMDBApiService
import com.alperencitak.discover_movies_app.model.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: TMDBApiService
) {
    suspend fun fetchMovies(page: Int): List<Movie> {
        return api.getMovies(page=page).results
    }
}