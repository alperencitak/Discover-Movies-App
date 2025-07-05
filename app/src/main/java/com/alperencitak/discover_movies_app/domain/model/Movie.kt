package com.alperencitak.discover_movies_app.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String,
    val vote_average: Float,
    val genres: List<Genre>
) {
    fun getFullPosterUrl(): String{
        return "https://image.tmdb.org/t/p/w500$poster_path"
    }
}