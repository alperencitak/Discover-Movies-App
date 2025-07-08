package com.alperencitak.discover_movies_app.domain.usecases.movies

import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class SelectMovies(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke(): Flow<List<Movie>>{
        return moviesRepository.selectMovies()
    }

}