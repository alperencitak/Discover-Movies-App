package com.alperencitak.discover_movies_app.presentation.categories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alperencitak.discover_movies_app.domain.usecases.movies.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases
): ViewModel() {

    private val _state = mutableStateOf(CategoryState())
    val state: State<CategoryState> = _state

    init {
        getGenres()
        getMovies()
    }

    fun onEvent(event: CategoryEvent){
        when(event){
            is CategoryEvent.UpdateGenre -> {
                _state.value = _state.value.copy(genreId = event.genreId)
                getMovies()
            }
            else -> {

            }
        }
    }

    private fun getMovies() {
        val movies = moviesUseCases.getMoviesByGenre(state.value.genreId).cachedIn(viewModelScope)
        _state.value = state.value.copy(movies = movies)
    }

    private fun getGenres() {
        viewModelScope.launch {
            val genres = moviesUseCases.getGenres().genres
            _state.value = _state.value.copy(allGenres = genres.filter { it.id != 28 })
        }
    }

}
