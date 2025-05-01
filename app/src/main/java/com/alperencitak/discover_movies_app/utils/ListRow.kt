package com.alperencitak.discover_movies_app.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.model.Movie
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.ui.theme.SoftRed

@Composable
fun ListRow(
    title: String,
    movies: List<Movie>,
    onClick: (Int) -> Unit,
    onSeeAllClick: () -> Unit
) {
    val nunito = FontFamily(
        Font(R.font.nunito_black)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = SoftRed,
                fontFamily = nunito
            )
            Text(
                text = if(title=="Top Rated" || title=="Week's Trends") "" else "See All",
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                color = SoftRed,
                fontFamily = nunito,
                modifier = Modifier.clickable {
                    onSeeAllClick()
                }
            )
        }
        LazyRow(
            modifier = Modifier.fillMaxHeight()
        ) {
            items(movies) { movie ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(2f/3f)
                        .padding(horizontal = 4.dp, vertical = 8.dp)
                        .clickable {
                            onClick(movie.id)
                        },
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = SoftBlack
                    )
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = movie.getFullPosterUrl()),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Movie Poster",
                        modifier = Modifier.fillMaxSize().background(SoftBlack)
                    )
                }
            }
        }
    }
}