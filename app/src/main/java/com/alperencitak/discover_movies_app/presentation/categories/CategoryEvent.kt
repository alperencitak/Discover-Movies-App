package com.alperencitak.discover_movies_app.presentation.categories

sealed class CategoryEvent {

    data class UpdateGenre(val genreId: Int): CategoryEvent()

    object GetMovies: CategoryEvent()

}