package com.alperencitak.discover_movies_app.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.ui.theme.SoftRed
import com.alperencitak.discover_movies_app.viewmodel.MovieViewModel

@Composable
fun MovieDetailScreen(movieId: Int = 1){
    val movieViewModel: MovieViewModel = hiltViewModel()
    val context = LocalContext.current
    val movieAsStateFlow = movieViewModel.movie.collectAsState()
    movieViewModel.getMovie(movieId)

    Box(
        modifier = Modifier.fillMaxSize().background(SoftBlack)
    )

    movieAsStateFlow.value?.let{ movie ->

        val trailerKey = movie.videos.firstOrNull{ video -> video.type == "Trailer" }?.key
        val videoTrailerUrl = trailerKey?.let { key -> "https://www.youtube.com/watch?v=$key" }

        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
        ) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((LocalConfiguration.current.screenHeightDp / 3).dp)
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
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp, vertical = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        //TO-DO
                    }
                ) {
                    Text(text = "WATCH", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = SoftRed)
                }
                Button(
                    onClick = {
                        videoTrailerUrl?.let{ url ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                    }
                ) {
                    Text(text = "TRAILER", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = SoftRed)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 32.dp)
            ){
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    color = SoftRed,
                    text = movie.movie.overview
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth().padding(32.dp)
            ){
                Text(
                    color = SoftRed,
                    text = "Actors"
                )
                LazyRow(
                    Modifier
                        .fillMaxWidth()
                        .height((LocalConfiguration.current.screenHeightDp / 3).dp)
                        .padding(vertical = 16.dp)
                ) {
                    items(movie.casts){ cast ->
                        ElevatedCard(
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(0.5f)
                                .padding(horizontal = 4.dp, vertical = 8.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = cast.getFullPosterPath()
                                ),
                                contentScale = ContentScale.Crop,
                                contentDescription = "Movie Poster",
                                modifier = Modifier.fillMaxSize()
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.White,
                                text = "\n${cast.name} : ${cast.character}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif,
                                textAlign = TextAlign.Center,
                                maxLines = 3
                            )
                        }
                    }
                }
            }
        }
    }
}