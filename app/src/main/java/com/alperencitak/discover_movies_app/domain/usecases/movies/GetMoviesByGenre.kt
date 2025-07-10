package com.alperencitak.discover_movies_app.domain.usecases.movies

import androidx.paging.PagingData
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesByGenre(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(genreId: Int): Flow<PagingData<Movie>>{
        return moviesRepository.getMoviesByGenre(genreId)
    }

}