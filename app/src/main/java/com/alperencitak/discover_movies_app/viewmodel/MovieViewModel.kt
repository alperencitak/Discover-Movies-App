package com.alperencitak.discover_movies_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alperencitak.discover_movies_app.model.Genre
import com.alperencitak.discover_movies_app.model.Movie
import com.alperencitak.discover_movies_app.model.MovieWithDetails
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

    private val _movie = MutableStateFlow<MovieWithDetails?>(null)
    val movie: StateFlow<MovieWithDetails?> = _movie

    private val _topRatedMovies = MutableStateFlow<List<Movie>>(emptyList())
    val topRatedMovies: StateFlow<List<Movie>> = _topRatedMovies

    private val _trendingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val trendingMovies: StateFlow<List<Movie>> = _trendingMovies

    private val _moviesByGenre = MutableStateFlow<Map<Int, List<Movie>>>(emptyMap())
    val moviesByGenre: StateFlow<Map<Int, List<Movie>>> = _moviesByGenre

    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres: StateFlow<List<Genre>> = _genres

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    init {
        getMovieGenres()
    }

    fun getMovies(page: Int) {
        viewModelScope.launch {
            try {
                _movies.value += repository.fetchMovies(page = page)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTopRatedMovies(page: Int) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _topRatedMovies.value += repository.fetchTopRatedMovies(page = page)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun getTrendingMovies(page: Int, timeWindow: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _trendingMovies.value += repository.fetchTrendingMoviesToday(
                    page = page,
                    timeWindow = timeWindow
                )
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun searchMovie(query: String, page: Int) {
        viewModelScope.launch {
            if(query.isEmpty()){
                getMovies(1)
            }else{
                try {
                    _loading.value = true
                    _movies.value = repository.searchMovie(
                        query = query,
                        page = page
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    _loading.value = false
                }
            }
        }
    }

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _movie.value = repository.fetchMovie(movieId)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun getFavoriteMovies(favoriteIds: Set<String>) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val list = emptyList<Movie>().toMutableList()
                favoriteIds.forEach { favoriteId ->
                    list += repository.fetchMovie(favoriteId.toInt()).movie
                }
                _movies.value = list
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun getLatestMovies(page: Int) {
        viewModelScope.launch {
            try {
                _movies.value = repository.fetchLatestMovies(page = page)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMoviesByGenre(page: Int, genreId: Int) {
        viewModelScope.launch {
            try {
                val movieListByGenreId = repository.fetchMoviesByGenre(page, genreId)
                _moviesByGenre.value = _moviesByGenre.value.toMutableMap().apply {
                    this[genreId] = (this[genreId] ?: emptyList()) + movieListByGenreId
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMovieGenres() {
        viewModelScope.launch {
            try {
                _genres.value = repository.fetchGenres()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}