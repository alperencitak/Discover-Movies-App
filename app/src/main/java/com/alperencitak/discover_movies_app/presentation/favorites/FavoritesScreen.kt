package com.alperencitak.discover_movies_app.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.presentation.common.CircularLoadingScreen
import com.alperencitak.discover_movies_app.presentation.common.MovieGridList
import com.alperencitak.discover_movies_app.presentation.favorites.components.EmptyFavoritesScreen
import com.alperencitak.discover_movies_app.ui.theme.SoftRed
import com.alperencitak.discover_movies_app.utils.MovieGridCard

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
                title = "Favorites",
                titleIcon = Icons.Default.Favorite,
                titleIconTint = SoftRed,
                isCountBarVisible = true,
                gridCells = 2,
                onClickItem = { navigateToDetails(it) }
            )
        }
    }

}