package com.alperencitak.discover_movies_app.domain.usecases.movies

import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class IsMovieFavorite(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(id: Int): Boolean{
        return moviesRepository.isMovieFavorite(id)
    }

}