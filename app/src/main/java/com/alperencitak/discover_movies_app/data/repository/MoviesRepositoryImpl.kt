package com.alperencitak.discover_movies_app.data.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alperencitak.discover_movies_app.data.local.MoviesDao
import com.alperencitak.discover_movies_app.data.remote.CategorizedMoviesPagingSource
import com.alperencitak.discover_movies_app.data.remote.MoviesApi
import com.alperencitak.discover_movies_app.data.remote.MoviesPagingSource
import com.alperencitak.discover_movies_app.data.remote.SearchMoviesPagingSource
import com.alperencitak.discover_movies_app.data.remote.dto.MovieResponse
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.domain.repository.MoviesRepository
import com.alperencitak.discover_movies_app.util.LanguageUtil
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(
    private val moviesApi: MoviesApi,
    private val moviesDao: MoviesDao,
    private val context: Context
): MoviesRepository {

    override fun getMovies(): Flow<PagingData<Movie>> {
        val pageSize = 20
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                prefetchDistance = 3,
                initialLoadSize = pageSize * 2
            ),
            pagingSourceFactory = {
                MoviesPagingSource(
                    moviesApi = moviesApi,
                    context = context
                )
            }
        ).flow
    }

    override fun searchMovies(searchQuery: String): Flow<PagingData<Movie>> {
        val pageSize = 20
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                prefetchDistance = 3,
                initialLoadSize = pageSize * 2
            ),
            pagingSourceFactory = {
                SearchMoviesPagingSource(
                    moviesApi = moviesApi,
                    context = context,
                    searchQuery = searchQuery
                )
            }
        ).flow
    }

    override fun getMoviesByGenre(genreId: Int): Flow<PagingData<Movie>> {
        val pageSize = 20
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                prefetchDistance = 3,
                initialLoadSize = pageSize * 2
            ),
            pagingSourceFactory = {
                CategorizedMoviesPagingSource(
                    moviesApi = moviesApi,
                    context = context,
                    genreId = genreId
                )
            }
        ).flow
    }

    override suspend fun getMovie(id: Int): MovieResponse {
        val language = LanguageUtil.getAppLanguage(context = context)
        val movie = moviesApi.getMovie(movieId=id, language=language)
        val videos = moviesApi.getMovieVideos(movieId=id, language=language).results
        val credits = moviesApi.getMovieCredits(movieId=id)
        val movieResponse = MovieResponse(
            movie=movie,
            videos=videos,
            casts=credits.cast,
            crews=credits.crew
        )
        return movieResponse
    }

    override suspend fun upsert(movie: Movie) {
        return moviesDao.upsert(movie)
    }

    override suspend fun delete(movie: Movie) {
        return moviesDao.delete(movie)
    }

    override fun selectMovies(): Flow<List<Movie>> {
        return moviesDao.selectMovies()
    }

    override suspend fun selectMovie(id: Int): Movie? {
        return moviesDao.selectMovie(id)
    }

    override suspend fun isMovieFavorite(id: Int): Boolean {
        return moviesDao.isMovieFavorite(id)
    }

}