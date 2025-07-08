package com.alperencitak.discover_movies_app.domain.usecases.movies

import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class SelectMovie(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(id: Int): Movie?{
        return moviesRepository.selectMovie(id)
    }

}