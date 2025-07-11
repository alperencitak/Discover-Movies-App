package com.alperencitak.discover_movies_app.presentation.search

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.presentation.common.MovieGridList
import com.alperencitak.discover_movies_app.presentation.common.SearchBar
import com.alperencitak.discover_movies_app.presentation.home.MovieGridCard
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.ui.theme.SoftRed

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Movie) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftBlack)
            .statusBarsPadding()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFF252836)
        ) {
            SearchBar(
                state = state,
                onValueChange = {
                    event(SearchEvent.UpdateSearchQuery(it))
                },
                onSearch = {
                    event(SearchEvent.SearchMovies)
                }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        state.movies?.let {
            val movies = it.collectAsLazyPagingItems()
            MovieGridList(movies = movies, onItemClick = { movie -> navigateToDetails(movie) })
        }
    }
}