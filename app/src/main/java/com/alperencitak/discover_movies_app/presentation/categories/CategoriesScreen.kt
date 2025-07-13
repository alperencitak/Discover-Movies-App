package com.alperencitak.discover_movies_app.presentation.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.alperencitak.discover_movies_app.domain.model.Genre
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.presentation.categories.components.CategoryTabsScreen
import com.alperencitak.discover_movies_app.presentation.common.CircularLoadingScreen
import com.alperencitak.discover_movies_app.presentation.common.MovieGridList
import com.alperencitak.discover_movies_app.presentation.home.HomeScreen

@Composable
fun CategoriesScreen(
    state: CategoryState,
    event: (CategoryEvent) -> Unit,
    categories: List<Genre>,
    navigateToDetails: (Movie) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1F1D2B),
                        Color(0xFF171621)
                    )
                )
            )
            .statusBarsPadding()
    ) {
        CategoryTabsScreen(
            categories = categories,
            selected = state.genreId,
            onCategorySelected = {
                event(CategoryEvent.UpdateGenre(genreId = it.id))
            }
        )
        state.movies?.let {
            val movies = it.collectAsLazyPagingItems()
            when (movies.loadState.refresh) {
                is LoadState.Loading -> {
                    CircularLoadingScreen()
                }
                is LoadState.Error -> {
                    val error = movies.loadState.refresh as LoadState.Error
                    // Error
                }
                else -> {
                    MovieGridList(movies = movies, onItemClick = { navigateToDetails(it) })
                }
            }
        }
    }

}