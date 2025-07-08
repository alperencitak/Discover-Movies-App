package com.alperencitak.discover_movies_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alperencitak.discover_movies_app.domain.model.Movie

@Database(entities = [Movie::class], version = 1)
@TypeConverters(MoviesTypeConverter::class)
abstract class MoviesDatabase: RoomDatabase(){

    abstract val moviesDao: MoviesDao

}