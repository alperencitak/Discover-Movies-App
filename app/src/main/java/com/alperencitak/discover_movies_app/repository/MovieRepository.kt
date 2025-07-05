package com.alperencitak.discover_movies_app.repository

import android.content.Context
//import com.alperencitak.discover_movies_app.api.TMDBApiService
import com.alperencitak.discover_movies_app.domain.model.Genre
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.data.remote.dto.MovieResponse
import java.util.Locale
import javax.inject.Inject

//class MovieRepository @Inject constructor(
//    private val api: TMDBApiService,
//    private val context: Context
//) {
//
//    private fun getAppLanguage(): String {
//        val locale: Locale = context.resources.configuration.locales[0]
//        return if (locale.language == "tr") {
//            "tr-TR"
//        } else {
//            "en-US"
//        }
//    }
//
//    suspend fun fetchMovies(page: Int): List<Movie> {
//        return api.getMovies(page=page, language=getAppLanguage()).results
//    }
//
//    suspend fun fetchMovie(movieId: Int): MovieResponse {
//        val movie = api.getMovie(movieId, language=getAppLanguage())
//        val videos = api.getMovieVideos(movieId, language=getAppLanguage()).results
//        val credits = api.getMovieCredits(movieId)
//        val movieResponse = MovieResponse(
//            movie=movie,
//            videos=videos,
//            casts=credits.cast,
//            crews=credits.crew
//        )
//        return movieResponse
//    }
//
//    suspend fun fetchTopRatedMovies(page: Int): List<Movie>{
//        return api.getTopRatedMovies(page=page, language=getAppLanguage()).results
//    }
//
//    suspend fun fetchTrendingMoviesToday(page: Int, timeWindow: String): List<Movie>{
//        return api.getTrendingMoviesToday(page=page, timeWindow=timeWindow, language=getAppLanguage()).results
//    }
//
//    suspend fun fetchLatestMovies(page: Int): List<Movie> {
//        return api.getLatestMovies(page=page, language=getAppLanguage()).results
//    }
//
//    suspend fun fetchMoviesByGenre(page: Int, genreId: Int): List<Movie> {
//        return api.getMoviesByGenre(page=page, genreId=genreId, language=getAppLanguage()).results
//    }
//
//    suspend fun fetchGenres(): List<Genre> {
//        return api.getMovieGenres(language=getAppLanguage()).genres
//    }
//
//    suspend fun searchMovie(query: String, page: Int): List<Movie> {
//        return api.searchMovie(query = query, page = page, language=getAppLanguage()).results
//    }
//
//}