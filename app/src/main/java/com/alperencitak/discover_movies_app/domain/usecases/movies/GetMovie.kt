package com.alperencitak.discover_movies_app.domain.usecases.movies

import com.alperencitak.discover_movies_app.data.remote.dto.MovieResponse
import com.alperencitak.discover_movies_app.domain.repository.MoviesRepository

class GetMovie(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(id: Int): MovieResponse{
        return moviesRepository.getMovie(id)
    }

}