package com.alperencitak.discover_movies_app.data.remote

import com.alperencitak.discover_movies_app.BuildConfig
import com.alperencitak.discover_movies_app.data.remote.dto.CreditsResponse
import com.alperencitak.discover_movies_app.data.remote.dto.GenreResponse
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.data.remote.dto.MovieListResponse
import com.alperencitak.discover_movies_app.data.remote.dto.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int
    ): MovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String,
    ): Movie

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String
    ): VideoResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): CreditsResponse

    @GET("movie/now_playing")
    suspend fun getLatestMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String = "primary_release_date.desc",
        @Query("page") page: Int
    ): MovieListResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieListResponse

    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMoviesToday(
        @Path("time_window") timeWindow: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieListResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("query") query: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieListResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("with_genres") genreId: Int,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int
    ): MovieListResponse

    @GET("discover/movie")
    suspend fun getMoviesByCrewMember(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("with_crew") crewId: Int,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int
    ): MovieListResponse

    @GET("discover/movie")
    suspend fun getMoviesByCast(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("with_cast") castId: Int,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int
    ): MovieListResponse

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String
    ): GenreResponse

}