package com.alperencitak.discover_movies_app.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.model.Movie
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.ui.theme.SoftGray
import com.alperencitak.discover_movies_app.ui.theme.SoftWhite
import java.util.Locale

@Composable
fun SearchedMovieItem(movie: Movie, onClick: () -> Unit) {
    val nunito = FontFamily(
        Font(R.font.nunito_black)
    )
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
    ) {
        ElevatedCard(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp, vertical = 8.dp)
                .clickable { onClick() },
            colors = CardDefaults.elevatedCardColors(
                containerColor = SoftBlack
            )
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
        Column(
            modifier = Modifier
                .weight(4f)
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            Text(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = nunito,
                text = movie.title,
                color = SoftGray,
                maxLines = 2
            )
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = getVoteColor(movie.vote_average)
                ),
                shape = androidx.compose.foundation.shape.CircleShape,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(
                    text = String.format(Locale.US, "%.2f", movie.vote_average),
                    color = SoftBlack,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = nunito
                )
            }
        }
    }
}