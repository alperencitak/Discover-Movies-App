package com.alperencitak.discover_movies_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alperencitak.discover_movies_app.repository.ProfileDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileDataStore: ProfileDataStore
): ViewModel(){

    private val _favorites = MutableStateFlow<Set<String>>(emptySet())
    val favorites: StateFlow<Set<String>> = _favorites

    init {
        loadFavorite()
    }

    fun loadFavorite(){
        viewModelScope.launch {
            profileDataStore.favoriteMovies.collect { favoriteMovies ->
                _favorites.value = favoriteMovies
            }
        }
    }

    fun addFavorite(movieId: String) {
        viewModelScope.launch {
            profileDataStore.addFavorite(movieId)
            loadFavorite()
        }
    }

    fun removeFavorite(movieId: String) {
        viewModelScope.launch {
            profileDataStore.removeFavorite(movieId)
            loadFavorite()
        }
    }

    fun isFavorite(movieId: String): Boolean{
        return _favorites.value.contains(movieId)
    }

}