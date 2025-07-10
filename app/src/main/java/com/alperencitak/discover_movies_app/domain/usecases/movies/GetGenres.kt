package com.alperencitak.discover_movies_app.domain.usecases.movies

import com.alperencitak.discover_movies_app.data.remote.dto.GenreResponse
import com.alperencitak.discover_movies_app.domain.repository.MoviesRepository

class GetGenres(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(): GenreResponse{
        return moviesRepository.getGenres()
    }

}