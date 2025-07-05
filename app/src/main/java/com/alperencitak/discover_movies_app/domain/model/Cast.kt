package com.alperencitak.discover_movies_app.domain.model

data class Cast(
    val name: String,
    val character: String,
    val id: Int,
    val profile_path: String?
){
    fun getFullPosterPath(): String{
        return "https://image.tmdb.org/t/p/w500$profile_path"
    }
}