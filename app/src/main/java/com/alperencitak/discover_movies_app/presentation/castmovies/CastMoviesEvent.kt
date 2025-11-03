package com.alperencitak.discover_movies_app.presentation.castmovies

sealed class CastMoviesEvent {

    data class UpdateCast(val castId: Int): CastMoviesEvent()

    object GetMovies: CastMoviesEvent()

}