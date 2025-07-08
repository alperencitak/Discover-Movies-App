package com.alperencitak.discover_movies_app.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alperencitak.discover_movies_app.domain.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * FROM Movie")
    fun selectMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM Movie WHERE id=:id")
    suspend fun selectMovie(id: Int): Movie?

    @Query("SELECT EXISTS(SELECT 1 FROM Movie WHERE id=:id)")
    suspend fun isMovieFavorite(id: Int): Boolean

}