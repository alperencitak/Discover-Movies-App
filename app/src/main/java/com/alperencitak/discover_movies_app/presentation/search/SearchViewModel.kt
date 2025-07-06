package com.alperencitak.discover_movies_app.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alperencitak.discover_movies_app.domain.usecases.movies.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.SearchMovies -> {
                searchMovies()
            }
        }
    }

    private fun searchMovies() {
        val movies = moviesUseCases.searchMovies(state.value.searchQuery).cachedIn(viewModelScope)
        _state.value = state.value.copy(movies = movies)
    }

}