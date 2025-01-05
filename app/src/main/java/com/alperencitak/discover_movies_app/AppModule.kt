package com.alperencitak.discover_movies_app

import android.app.Application
import android.content.Context
import com.alperencitak.discover_movies_app.api.RetrofitInstance
import com.alperencitak.discover_movies_app.api.TMDBApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    fun provideTMDBApiService(): TMDBApiService {
        return RetrofitInstance.api
    }
}