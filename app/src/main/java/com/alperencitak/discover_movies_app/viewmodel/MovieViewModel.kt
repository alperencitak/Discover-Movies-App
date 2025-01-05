package com.alperencitak.discover_movies_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alperencitak.discover_movies_app.model.Movie
import com.alperencitak.discover_movies_app.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    init {
        getMovies(1)
    }

    fun getMovies(page: Int) {
        viewModelScope.launch {
            try {
                _movies.value = repository.fetchMovies(page=page)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}