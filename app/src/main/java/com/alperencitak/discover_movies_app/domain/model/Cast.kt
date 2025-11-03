package com.alperencitak.discover_movies_app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cast(
    val name: String,
    val character: String,
    val id: Int,
    val profile_path: String?
): Parcelable {
    fun getFullPosterPath(): String{
        return "https://image.tmdb.org/t/p/w500$profile_path"
    }
}