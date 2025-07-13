package com.alperencitak.discover_movies_app.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.presentation.common.MovieGridList
import com.alperencitak.discover_movies_app.presentation.common.SearchBar
import com.alperencitak.discover_movies_app.presentation.search.components.EmptyScreen
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack

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
        if(state.searchQuery.isEmpty()){
            EmptyScreen(
                icon = R.drawable.search_menu,
                text = stringResource(R.string.no_search_query)
            )
        }else{
            state.movies?.let {
                val movies = it.collectAsLazyPagingItems()
                if(movies.itemCount == 0){
                    EmptyScreen(
                        icon = R.drawable.search_cancel,
                        text = stringResource(R.string.empty_movies)
                    )
                }else{
                    MovieGridList(movies = movies, onItemClick = { movie -> navigateToDetails(movie) })
                }
            }
        }

    }
}