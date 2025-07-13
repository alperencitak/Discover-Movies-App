package com.alperencitak.discover_movies_app.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.presentation.common.MovieGridList
import com.alperencitak.discover_movies_app.presentation.favorites.components.EmptyFavoritesScreen
import com.alperencitak.discover_movies_app.ui.theme.SoftRed

@Composable
fun FavoritesScreen(
    state: FavoritesState,
    navigateToDetails: (Movie) -> Unit,
    navigateToHome: () -> Unit
) {
    Box(
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
    ) {
        val movies = state.movies
        if(movies.isEmpty()){
            EmptyFavoritesScreen(
                onExploreClick = { navigateToHome() }
            )
        }else{
            MovieGridList(
                movies = state.movies,
                title = stringResource(R.string.favorites),
                titleIcon = Icons.Default.Favorite,
                titleIconTint = SoftRed,
                isCountBarVisible = true,
                gridCells = 2,
                onClickItem = { navigateToDetails(it) }
            )
        }
    }

}