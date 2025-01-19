package com.alperencitak.discover_movies_app.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.alperencitak.discover_movies_app.model.Movie
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = movie.getFullPosterUrl()),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Movie Poster",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f/3f)
                .background(SoftBlack)
        )
    }
}