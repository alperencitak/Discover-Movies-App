package com.alperencitak.discover_movies_app.domain.repository

import androidx.paging.PagingData
import com.alperencitak.discover_movies_app.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMovies(): Flow<PagingData<Movie>>

}