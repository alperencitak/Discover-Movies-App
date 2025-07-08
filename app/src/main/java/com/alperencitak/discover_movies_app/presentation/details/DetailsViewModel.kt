package com.alperencitak.discover_movies_app.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alperencitak.discover_movies_app.data.remote.dto.MovieResponse
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.domain.usecases.movies.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases
) : ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    var isFavorite by mutableStateOf(false)
        private set

    var movieResponse by mutableStateOf<MovieResponse?>(null)

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteMovie -> {
                viewModelScope.launch {
                    val movie = moviesUseCases.selectMovie(event.movie.id)
                    if (movie == null) {
                        upsert(event.movie)
                    } else {
                        delete(event.movie)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
            is DetailsEvent.CheckIfFavorite -> {
                viewModelScope.launch {
                    isFavorite = moviesUseCases.isMovieFavorite(event.id)
                }
            }
        }
    }

    suspend fun fetchMovie(id: Int){
        movieResponse = moviesUseCases.getMovie(id)
    }

    private suspend fun upsert(movie: Movie) {
        moviesUseCases.upsertMovie(movie)
        isFavorite = true
        sideEffect = "Movie added to favorites."
    }

    private suspend fun delete(movie: Movie) {
        moviesUseCases.deleteMovie(movie)
        isFavorite = false
        sideEffect = "Movie removed from favorites."
    }

}