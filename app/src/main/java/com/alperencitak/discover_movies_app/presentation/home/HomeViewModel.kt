package com.alperencitak.discover_movies_app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alperencitak.discover_movies_app.domain.usecases.movies.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases
): ViewModel() {

    val movies = moviesUseCases.getMovies().cachedIn(viewModelScope)

    val topRatedMovies = moviesUseCases.getTopRatedMovies().cachedIn(viewModelScope)

}