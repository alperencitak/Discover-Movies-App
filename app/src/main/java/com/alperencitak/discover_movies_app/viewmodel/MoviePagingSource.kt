package com.alperencitak.discover_movies_app.viewmodel

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alperencitak.discover_movies_app.model.Movie
import com.alperencitak.discover_movies_app.repository.MovieRepository

class MoviePagingSource(
    private val repository: MovieRepository
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = repository.fetchMovies(page)

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}