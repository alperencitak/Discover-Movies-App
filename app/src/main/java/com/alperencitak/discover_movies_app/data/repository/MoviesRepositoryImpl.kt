package com.alperencitak.discover_movies_app.data.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alperencitak.discover_movies_app.data.remote.MoviesApi
import com.alperencitak.discover_movies_app.data.remote.MoviesPagingSource
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(
    private val moviesApi: MoviesApi,
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

}