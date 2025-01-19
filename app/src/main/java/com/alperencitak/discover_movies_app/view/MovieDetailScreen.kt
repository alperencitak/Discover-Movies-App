package com.alperencitak.discover_movies_app.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.ui.theme.SoftGray
import com.alperencitak.discover_movies_app.ui.theme.SoftRed
import com.alperencitak.discover_movies_app.ui.theme.SoftWhite
import com.alperencitak.discover_movies_app.utils.CircularLoadingScreen
import com.alperencitak.discover_movies_app.utils.getVoteColor
import com.alperencitak.discover_movies_app.viewmodel.MovieViewModel
import com.alperencitak.discover_movies_app.viewmodel.ProfileViewModel

@Composable
fun MovieDetailScreen(movieId: Int = 1) {
    val movieViewModel: MovieViewModel = hiltViewModel()
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val context = LocalContext.current
    val favorites by profileViewModel.favorites.collectAsState()
    val isFavorite = favorites.contains(movieId.toString())
    val movieAsStateFlow = movieViewModel.movie.collectAsState()
    movieViewModel.getMovie(movieId)

    val nunito = FontFamily(
        Font(R.font.nunito_black)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftBlack)
    )

    if(movieAsStateFlow.value == null){
        CircularLoadingScreen()
    }

    movieAsStateFlow.value?.let { movie ->

        val trailerKey by remember { mutableStateOf(movie.videos.firstOrNull { video -> video.type == "Trailer" }?.key) }
        val videoTrailerUrl by remember { mutableStateOf(trailerKey?.let { key -> "https://www.youtube.com/embed/$key" }) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
                    .padding(bottom = 32.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(movie.movie.getFullPosterUrl()),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Image Poster",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 8.dp, start = 32.dp, end = 32.dp)
            ) {
                Text(
                    text = movie.movie.genres.joinToString(" / ") { it.name },
                    color = SoftGray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = nunito
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = getVoteColor(movie.movie.vote_average)
                    ),
                    shape = androidx.compose.foundation.shape.CircleShape
                ) {
                    Text(
                        text = movie.movie.vote_average.toString(),
                        color = SoftBlack,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = nunito
                    )
                }
                Button(
                    onClick = {
                        videoTrailerUrl?.let { url ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (videoTrailerUrl != null) SoftRed else Color.DarkGray
                    ),
                    shape = androidx.compose.foundation.shape.AbsoluteCutCornerShape(0)
                ) {
                    Text(
                        text = stringResource(R.string.trailer),
                        color = SoftBlack,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = nunito
                    )
                }
                IconButton(onClick = {
                    if (isFavorite){
                        profileViewModel.removeFavorite(movieId.toString())
                    }else{
                        profileViewModel.addFavorite(movieId.toString())
                    }
                }) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "Favorite Icon",
                        tint = if (isFavorite) Color.Yellow else Color.Gray
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    color = SoftGray,
                    fontSize = 18.sp,
                    fontFamily = nunito,
                    text = movie.movie.overview
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    color = SoftRed,
                    text = stringResource(R.string.actors),
                    fontSize = 20.sp,
                    fontFamily = nunito
                )
                HorizontalDivider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                LazyRow(
                    Modifier
                        .fillMaxWidth()
                        .height((LocalConfiguration.current.screenHeightDp / 3).dp)
                        .padding(bottom = 16.dp)
                ) {
                    items(movie.casts) { cast ->
                        ElevatedCard(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(200.dp)
                                .padding(horizontal = 4.dp, vertical = 8.dp),
                            colors = CardDefaults.elevatedCardColors(
                                containerColor = SoftBlack
                            )
                        ) {
                            Text(
                                color = SoftGray,
                                text = cast.name,
                                fontWeight = FontWeight.Bold,
                                fontFamily = nunito,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                minLines = 1,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                            )
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = cast.getFullPosterPath()
                                ),
                                contentScale = ContentScale.Crop,
                                contentDescription = "Movie Poster",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}