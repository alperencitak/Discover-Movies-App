package com.alperencitak.discover_movies_app.domain.repository

import androidx.paging.PagingData
import com.alperencitak.discover_movies_app.data.remote.dto.GenreResponse
import com.alperencitak.discover_movies_app.data.remote.dto.MovieResponse
import com.alperencitak.discover_movies_app.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMovies(): Flow<PagingData<Movie>>

    fun getTopRatedMovies(): Flow<PagingData<Movie>>

    fun searchMovies(searchQuery: String): Flow<PagingData<Movie>>

    fun getMoviesByGenre(genreId: Int): Flow<PagingData<Movie>>

    fun getMoviesByCast(castId: Int): Flow<PagingData<Movie>>

    suspend fun getGenres(): GenreResponse

    suspend fun getMovie(id: Int): MovieResponse

    suspend fun upsert(movie: Movie)

    suspend fun delete(movie: Movie)

    fun selectMovies(): Flow<List<Movie>>

    suspend fun selectMovie(id: Int): Movie?

    suspend fun isMovieFavorite(id: Int): Boolean

}