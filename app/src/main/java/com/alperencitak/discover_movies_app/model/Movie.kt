package com.alperencitak.discover_movies_app.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String
) {
    fun getFullPosterUrl(): String{
        return "https://image.tmdb.org/t/p/w500$poster_path"
    }
}