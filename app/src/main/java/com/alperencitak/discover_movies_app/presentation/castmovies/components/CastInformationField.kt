package com.alperencitak.discover_movies_app.presentation.castmovies.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.domain.model.Cast
import com.alperencitak.discover_movies_app.domain.model.Crew
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.presentation.castmovies.CastMoviesEvent
import com.alperencitak.discover_movies_app.presentation.common.CastCard
import com.alperencitak.discover_movies_app.presentation.common.ChipInfo
import com.alperencitak.discover_movies_app.presentation.common.RatingBar
import com.alperencitak.discover_movies_app.ui.theme.SoftRed

@Composable
fun CastInformationField(
    cast: Cast
) {
    val nunito = FontFamily(Font(R.font.nunito_black))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = if(movie != null && movie.title.isNotBlank()) movie.title else
                    stringResource(R.string.unnamed),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontFamily = nunito,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            movie?.release_date?.take(4)?.let { year ->
                ChipInfo(
                    icon = R.drawable.date_range_icon,
                    text = year,
                    nunito = nunito
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = if(movie != null && movie.overview.isNotBlank()) movie.overview else
                stringResource(R.string.no_movie_info),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontFamily = nunito,
                color = Color.White.copy(alpha = 0.7f)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (!casts.isNullOrEmpty()) {
            Text(
                text = stringResource(R.string.actors),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = nunito,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(casts) { cast ->
                    CastCard(cast = cast, nunito = nunito)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "This product uses the TMDb API but is not endorsed or certified by TMDb.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontFamily = nunito,
                color = SoftRed.copy(alpha = 0.7f)
            )
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}