package com.alperencitak.discover_movies_app.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.alperencitak.discover_movies_app.domain.model.Genre

@ProvidedTypeConverter
class MoviesTypeConverter {

    @TypeConverter
    fun genresToString(genres: List<Genre>): String {
        return genres.joinToString(";") { "${it.id},${it.name}" }
    }

    @TypeConverter
    fun stringToGenres(genreString: String): List<Genre> {
        if (genreString.isBlank()) return emptyList()
        return genreString.split(";").map {
            val genreParts = it.split(",")
            Genre(
                id = genreParts[0].toInt(),
                name = genreParts[1]
            )
        }
    }

}