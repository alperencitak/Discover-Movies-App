package com.alperencitak.discover_movies_app.api

import com.alperencitak.discover_movies_app.BuildConfig
import com.alperencitak.discover_movies_app.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApiService {
    @GET("3/discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int
    ): MovieResponse
}