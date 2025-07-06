package com.alperencitak.discover_movies_app.data.remote

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.util.LanguageUtil

class SearchMoviesPagingSource(
    private val moviesApi: MoviesApi,
    private val searchQuery: String,
    private val context: Context
): PagingSource<Int, Movie>() {

    private var totalMoviesCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val language = LanguageUtil.getAppLanguage(context = context)
            val movieListResponse = moviesApi.searchMovie(
                query = searchQuery,
                page = page,
                language = language
            )
            totalMoviesCount += movieListResponse.results.size
            val movies = movieListResponse.results
            LoadResult.Page(
                data = movies,
                nextKey = if (totalMoviesCount == movieListResponse.totalResults) null else page + 1,
                prevKey = null
            )
        }catch (e: Exception){
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}