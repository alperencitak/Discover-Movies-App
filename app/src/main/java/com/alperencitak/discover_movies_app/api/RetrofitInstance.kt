package com.alperencitak.discover_movies_app.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object RetrofitInstance {
//    private const val BASE_URL = "https://api.themoviedb.org/3/"
//
//    val api: TMDBApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(TMDBApiService::class.java)
//    }
//}