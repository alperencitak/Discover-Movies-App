package com.alperencitak.discover_movies_app.domain.usecases.movies

import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.domain.repository.MoviesRepository

class DeleteMovie(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(movie: Movie){
        moviesRepository.delete(movie)
    }

}