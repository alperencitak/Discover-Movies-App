package com.alperencitak.discover_movies_app.repository

import android.content.Context
import com.alperencitak.discover_movies_app.api.TMDBApiService
import com.alperencitak.discover_movies_app.model.Genre
import com.alperencitak.discover_movies_app.model.Movie
import com.alperencitak.discover_movies_app.model.MovieWithDetails
import org.intellij.lang.annotations.Language
import java.util.Locale
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: TMDBApiService,
    private val context: Context
) {

    private fun getAppLanguage(): String {
        val locale: Locale = context.resources.configuration.locales[0]
        return if (locale.language == "tr") {
            "tr-TR"
        } else {
            "en-US"
        }
    }

    suspend fun fetchMovies(page: Int): List<Movie> {
        return api.getMovies(page=page, language=getAppLanguage()).results
    }

    suspend fun fetchMovie(movieId: Int): MovieWithDetails{
        val movie = MovieWithDetails(
            movie=api.getMovie(movieId, language=getAppLanguage()),
            videos=api.getMovieVideos(movieId, language=getAppLanguage()).results,
            casts=api.getMovieCredits(movieId).cast,
            crews=api.getMovieCredits(movieId).crew
        )
        return movie
    }

    suspend fun fetchTopRatedMovies(page: Int): List<Movie>{
        return api.getTopRatedMovies(page=page, language=getAppLanguage()).results
    }

    suspend fun fetchTrendingMoviesToday(page: Int, timeWindow: String): List<Movie>{
        return api.getTrendingMoviesToday(page=page, timeWindow=timeWindow, language=getAppLanguage()).results
    }

    suspend fun fetchLatestMovies(page: Int): List<Movie> {
        return api.getLatestMovies(page=page, language=getAppLanguage()).results
    }

    suspend fun fetchMoviesByGenre(page: Int, genreId: Int): List<Movie> {
        return api.getMoviesByGenre(page=page, genreId=genreId, language=getAppLanguage()).results
    }

    suspend fun fetchGenres(): List<Genre> {
        return api.getMovieGenres(language=getAppLanguage()).genres
    }

    suspend fun searchMovie(query: String, page: Int): List<Movie> {
        return api.searchMovie(query = query, page = page, language=getAppLanguage()).results
    }

}