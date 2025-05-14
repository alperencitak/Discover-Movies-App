package com.alperencitak.discover_movies_app.utils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alperencitak.discover_movies_app.model.Movie
import com.alperencitak.discover_movies_app.ui.theme.SoftRed

@Composable
fun CategorySection(
    title: String,
    movies: List<Movie>,
    onSeeAllClick: () -> Unit,
    onMovieClick: (Int) -> Unit,
    nunito: FontFamily
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = nunito,
                    fontWeight = FontWeight.Bold,
                    color = SoftRed,
                    fontSize = 20.sp
                )
            )
            if(title != "Top Rated Movies" && title != "Trending This Week"){
                TextButton(onClick = onSeeAllClick) {
                    Text(
                        text = "See All",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = nunito,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    )
                }
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(movies.filter { it.poster_path != null }) { movie ->
                CategoryMovieCard(
                    movie = movie,
                    onClick = { onMovieClick(movie.id) },
                    nunito = nunito
                )
            }
        }
    }
}