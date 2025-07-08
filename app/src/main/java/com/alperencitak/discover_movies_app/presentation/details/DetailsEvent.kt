package com.alperencitak.discover_movies_app.presentation.details

import com.alperencitak.discover_movies_app.domain.model.Movie

sealed class DetailsEvent {

    data class UpsertDeleteMovie(val movie: Movie): DetailsEvent()

    data class CheckIfFavorite(val id: Int): DetailsEvent()

    object RemoveSideEffect: DetailsEvent()

}