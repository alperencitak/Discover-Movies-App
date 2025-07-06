package com.alperencitak.discover_movies_app

import android.app.Application
import android.content.Context
import com.alperencitak.discover_movies_app.data.remote.MoviesApi
import com.alperencitak.discover_movies_app.data.repository.MoviesRepositoryImpl
import com.alperencitak.discover_movies_app.domain.repository.MoviesRepository
import com.alperencitak.discover_movies_app.domain.usecases.movies.GetMovies
import com.alperencitak.discover_movies_app.domain.usecases.movies.MoviesUseCases
import com.alperencitak.discover_movies_app.domain.usecases.movies.SearchMovies
import com.alperencitak.discover_movies_app.util.Constants.BASE_URL
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
    fun provideMoviesRepository(
        moviesApi: MoviesApi,
        context: Context
    ): MoviesRepository = MoviesRepositoryImpl(moviesApi = moviesApi, context = context)

    @Provides
    @Singleton
    fun provideMoviesUseCases(
        moviesRepository: MoviesRepository
    ): MoviesUseCases{
        return MoviesUseCases(
            getMovies = GetMovies(moviesRepository),
            searchMovies = SearchMovies(moviesRepository)
        )
    }

}