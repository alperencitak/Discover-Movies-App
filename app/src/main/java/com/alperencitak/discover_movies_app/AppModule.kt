package com.alperencitak.discover_movies_app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.alperencitak.discover_movies_app.data.local.MoviesDao
import com.alperencitak.discover_movies_app.data.local.MoviesDatabase
import com.alperencitak.discover_movies_app.data.local.MoviesTypeConverter
import com.alperencitak.discover_movies_app.data.remote.MoviesApi
import com.alperencitak.discover_movies_app.data.repository.MoviesRepositoryImpl
import com.alperencitak.discover_movies_app.domain.repository.MoviesRepository
import com.alperencitak.discover_movies_app.domain.usecases.movies.DeleteMovie
import com.alperencitak.discover_movies_app.domain.usecases.movies.GetMovie
import com.alperencitak.discover_movies_app.domain.usecases.movies.GetMovies
import com.alperencitak.discover_movies_app.domain.usecases.movies.GetMoviesByGenre
import com.alperencitak.discover_movies_app.domain.usecases.movies.IsMovieFavorite
import com.alperencitak.discover_movies_app.domain.usecases.movies.MoviesUseCases
import com.alperencitak.discover_movies_app.domain.usecases.movies.SearchMovies
import com.alperencitak.discover_movies_app.domain.usecases.movies.SelectMovie
import com.alperencitak.discover_movies_app.domain.usecases.movies.SelectMovies
import com.alperencitak.discover_movies_app.domain.usecases.movies.UpsertMovie
import com.alperencitak.discover_movies_app.util.Constants.BASE_URL
import com.alperencitak.discover_movies_app.util.Constants.MOVIES_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(
        app: Application
    ): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideMoviesApi(): MoviesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesDatabase(
        application: Application
    ): MoviesDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = MoviesDatabase::class.java,
            name = MOVIES_DATABASE_NAME
        ).addTypeConverter(MoviesTypeConverter())
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(
        moviesDatabase: MoviesDatabase
    ): MoviesDao = moviesDatabase.moviesDao

    @Provides
    @Singleton
    fun provideMoviesRepository(
        moviesApi: MoviesApi,
        moviesDao: MoviesDao,
        context: Context
    ): MoviesRepository = MoviesRepositoryImpl(
        moviesApi = moviesApi,
        moviesDao = moviesDao,
        context = context
    )

    @Provides
    @Singleton
    fun provideMoviesUseCases(
        moviesRepository: MoviesRepository
    ): MoviesUseCases {
        return MoviesUseCases(
            getMovies = GetMovies(moviesRepository),
            searchMovies = SearchMovies(moviesRepository),
            getMoviesByGenre = GetMoviesByGenre(moviesRepository),
            getMovie = GetMovie(moviesRepository),
            upsertMovie = UpsertMovie(moviesRepository),
            deleteMovie = DeleteMovie(moviesRepository),
            selectMovies = SelectMovies(moviesRepository),
            selectMovie = SelectMovie(moviesRepository),
            isMovieFavorite = IsMovieFavorite(moviesRepository),
        )
    }

}