package com.alperencitak.discover_movies_app.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.ui.theme.SoftRed

@Composable
fun MovieGridList(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    title: String = "",
    titleIcon: ImageVector = Icons.Default.KeyboardArrowDown,
    titleIconTint: Color = Color.Black,
    isCountBarVisible: Boolean = false,
    gridCells: Int = 3,
    onClickItem: (Movie) -> Unit
) {
    val nunito = FontFamily(Font(R.font.nunito_black))
    LazyVerticalGrid(
        columns = GridCells.Fixed(gridCells),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 24.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize().statusBarsPadding()
    ) {
        if (title.isNotEmpty()){
            item(span = { GridItemSpan(2) }) {
                Column(
                    modifier = Modifier.padding(top = 48.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = titleIcon,
                            contentDescription = null,
                            tint = titleIconTint
                        )
                        Text(
                            text = "Favorites",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = nunito,
                                fontWeight = FontWeight.Bold,
                                color = SoftRed,
                                fontSize = 24.sp
                            )
                        )
                    }
                    if(isCountBarVisible){
                        Text(
                            text = "${movies.size} Movies",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = nunito,
                                color = Color.White.copy(alpha = 0.7f),
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                }
            }
        }
        items(movies){ movie ->
            MovieGridCard(movie = movie, onClick = { onClickItem(movie) })
        }
    }
}

@Composable
fun MovieGridList(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<Movie>,
    gridCells: Int = 3,
    onItemClick: (Movie) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(gridCells),
        modifier = modifier
            .padding(horizontal = 12.dp),
        contentPadding = PaddingValues(bottom = 80.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(count = movies.itemCount){
            movies[it]?.let { movie ->
                MovieGridCard(movie = movie, onClick = { onItemClick(movie) })
            }
        }
    }
}