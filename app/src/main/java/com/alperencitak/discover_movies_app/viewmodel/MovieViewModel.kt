package com.alperencitak.discover_movies_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alperencitak.discover_movies_app.model.Genre
import com.alperencitak.discover_movies_app.model.Movie
import com.alperencitak.discover_movies_app.model.MovieWithDetails
import com.alperencitak.discover_movies_app.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val pagedMovies: Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 2,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
        pagingSourceFactory = { MoviePagingSource(repository) }
    ).flow.cachedIn(viewModelScope)

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _searchedMovies = MutableStateFlow<List<Movie>>(emptyList())
    val searchedMovies: StateFlow<List<Movie>> = _searchedMovies

    private val _favoriteMovies = MutableStateFlow<List<Movie>>(emptyList())
    val favoriteMovies: StateFlow<List<Movie>> = _favoriteMovies

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

    object MovieCache {
        val cachedMovies = mutableListOf<Movie>()
        val cachedGenres = mutableListOf<Genre>()
        val cachedMoviesByGenre = mutableMapOf<Int, List<Movie>>()
        val cachedTopRatedMovies = mutableListOf<Movie>()
        val cachedTrendingMovies = mutableListOf<Movie>()
    }

    fun getMovies(page: Int) {
        viewModelScope.launch {
            try {
                if ((MovieCache.cachedMovies.size/20) < page) {
                    MovieCache.cachedMovies.addAll(repository.fetchMovies(page))
                }
                _movies.value = MovieCache.cachedMovies.toList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTopRatedMovies(page: Int) {
        viewModelScope.launch {
            try {
                _loading.value = true
                if (MovieCache.cachedTopRatedMovies.isEmpty()){
                    MovieCache.cachedTopRatedMovies.addAll(repository.fetchTopRatedMovies(page))
                }
                _topRatedMovies.value = MovieCache.cachedTopRatedMovies
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
                if (MovieCache.cachedTrendingMovies.isEmpty()){
                    MovieCache.cachedTrendingMovies.addAll(repository.fetchTrendingMoviesToday(
                        page = page,
                        timeWindow = timeWindow)
                    )
                }
                _trendingMovies.value = MovieCache.cachedTrendingMovies
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun searchMovie(query: String, page: Int) {
        viewModelScope.launch {
            try {
                _loading.value = true
                if (query.isBlank()){
                    val char = listOf("a", "b", "c", "d", "e").random()
                    _searchedMovies.value += repository.searchMovie(
                        query = char,
                        page = page
                    )
                }else if (page == 1){
                    _searchedMovies.value = repository.searchMovie(
                        query = query,
                        page = page
                    )
                }else{
                    _searchedMovies.value += repository.searchMovie(
                        query = query,
                        page = page
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
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
                _favoriteMovies.value = list
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                println(_favoriteMovies.value.size)
                _loading.value = false
            }
        }
    }

    fun getLatestMovies(page: Int = 1) {
        viewModelScope.launch {
            try {
                _movies.value = repository.fetchLatestMovies(page = page)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMoviesByGenre(page: Int = 1, genreId: Int, expandable: Boolean) {
        viewModelScope.launch {
            try {
                if (expandable){
                    MovieCache.cachedMoviesByGenre[genreId] = (MovieCache.cachedMoviesByGenre[genreId] ?: emptyList()) + repository.fetchMoviesByGenre(page=page, genreId=genreId)
                }else{
                    MovieCache.cachedMoviesByGenre[genreId] = repository.fetchMoviesByGenre(page=page, genreId=genreId)
                }
                if(MovieCache.cachedMoviesByGenre[genreId].isNullOrEmpty()){
                    MovieCache.cachedMoviesByGenre[genreId] = repository.fetchMoviesByGenre(page=page, genreId=genreId)
                }
                _moviesByGenre.value = MovieCache.cachedMoviesByGenre
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMovieGenres() {
        viewModelScope.launch {
            try {
                if(MovieCache.cachedGenres.isEmpty()){
                    MovieCache.cachedGenres.addAll(repository.fetchGenres())
                }
                _genres.value = MovieCache.cachedGenres.toList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}