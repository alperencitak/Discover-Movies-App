package com.alperencitak.discover_movies_app.domain.usecases.movies

import androidx.paging.PagingData
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetMovies(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke(): Flow<PagingData<Movie>>{
        return moviesRepository.getMovies()
    }

}