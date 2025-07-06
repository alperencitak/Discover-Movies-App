package com.alperencitak.discover_movies_app.presentation.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String): SearchEvent()

    object SearchMovies: SearchEvent()

}