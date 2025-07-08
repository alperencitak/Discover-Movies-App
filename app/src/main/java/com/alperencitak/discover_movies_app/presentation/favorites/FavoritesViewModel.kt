package com.alperencitak.discover_movies_app.presentation.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alperencitak.discover_movies_app.domain.usecases.movies.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases
): ViewModel() {

    private val _state = mutableStateOf(FavoritesState())
    val state: State<FavoritesState> = _state

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies(){
        moviesUseCases.selectMovies().onEach {
            _state.value = _state.value.copy(movies = it.asReversed())
        }.launchIn(viewModelScope)
    }

}