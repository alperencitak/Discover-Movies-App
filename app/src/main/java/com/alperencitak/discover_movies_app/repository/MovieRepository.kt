package com.alperencitak.discover_movies_app.repository

import com.alperencitak.discover_movies_app.api.TMDBApiService
import com.alperencitak.discover_movies_app.model.Genre
import com.alperencitak.discover_movies_app.model.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: TMDBApiService
) {
    suspend fun fetchMovies(page: Int): List<Movie> {
        return api.getMovies(page=page).results
    }

    suspend fun fetchLatestMovies(page: Int): List<Movie> {
        return api.getLatestMovies(page=page).results
    }

    suspend fun fetchMoviesByGenre(page: Int, genreId: Int): List<Movie> {
        return api.getMoviesByGenre(page=page, genreId=genreId).results
    }

    suspend fun fetchGenres(): List<Genre> {
        return api.getMovieGenres().genres
    }
}