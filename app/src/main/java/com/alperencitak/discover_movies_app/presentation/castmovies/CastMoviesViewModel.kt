package com.alperencitak.discover_movies_app.presentation.castmovies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alperencitak.discover_movies_app.domain.usecases.movies.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CastMoviesViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases
): ViewModel() {

    private val _state = mutableStateOf(CastMoviesState())
    val state: State<CastMoviesState> = _state

    init {
        getMovies()
    }

    fun onEvent(event: CastMoviesEvent){
        when(event){
            is CastMoviesEvent.UpdateCast -> {
                _state.value = _state.value.copy(castId = event.castId)
                getMovies()
            }
            else -> {

            }
        }
    }

    private fun getMovies() {
        val movies = moviesUseCases.getMoviesByCast(state.value.castId).cachedIn(viewModelScope)
        _state.value = state.value.copy(movies = movies)
    }

}
