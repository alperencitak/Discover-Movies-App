package com.alperencitak.discover_movies_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alperencitak.discover_movies_app.model.Genre
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

    private val _moviesByGenre = MutableStateFlow<List<Movie>>(emptyList())
    val moviesByGenre: StateFlow<List<Movie>> = _moviesByGenre

    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres: StateFlow<List<Genre>> = _genres

    init {
        getMovieGenres()
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

    fun getLatestMovies(page: Int) {
        viewModelScope.launch {
            try {
                _movies.value = repository.fetchLatestMovies(page=page)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMoviesByGenre(page: Int, genreId: Int){
        viewModelScope.launch {
            try {
                _moviesByGenre.value = repository.fetchMoviesByGenre(page, genreId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMovieGenres(){
        viewModelScope.launch {
            try {
                _genres.value = repository.fetchGenres()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}