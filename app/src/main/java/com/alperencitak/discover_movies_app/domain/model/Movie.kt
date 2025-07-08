package com.alperencitak.discover_movies_app.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String,
    val vote_average: Float,
    val genres: List<Genre>
): Parcelable {
    fun getFullPosterUrl(): String{
        return "https://image.tmdb.org/t/p/w500$poster_path"
    }
}