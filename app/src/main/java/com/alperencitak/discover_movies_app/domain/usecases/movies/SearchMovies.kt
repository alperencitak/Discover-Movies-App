package com.alperencitak.discover_movies_app.domain.usecases.movies

import androidx.paging.PagingData
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class SearchMovies(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke(searchQuery: String): Flow<PagingData<Movie>>{
        return moviesRepository.searchMovies(searchQuery=searchQuery)
    }

}