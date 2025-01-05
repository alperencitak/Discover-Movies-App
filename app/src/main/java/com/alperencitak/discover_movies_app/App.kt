package com.alperencitak.discover_movies_app

import android.app.Application
import com.alperencitak.discover_movies_app.repository.MovieRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var movieRepository: MovieRepository

}