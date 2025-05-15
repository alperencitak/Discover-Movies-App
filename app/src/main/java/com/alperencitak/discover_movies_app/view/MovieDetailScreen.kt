package com.alperencitak.discover_movies_app.view

import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.ui.theme.SoftRed
import com.alperencitak.discover_movies_app.utils.CircularLoadingScreen
import com.alperencitak.discover_movies_app.viewmodel.MovieViewModel
import com.alperencitak.discover_movies_app.viewmodel.ProfileViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alperencitak.discover_movies_app.ui.theme.SoftDarkBlue
import com.alperencitak.discover_movies_app.utils.CastCard
import com.alperencitak.discover_movies_app.utils.ChipInfo
import com.alperencitak.discover_movies_app.utils.RatingBar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun MovieDetailScreen(
    navController: NavHostController,
    movieId: Int
) {
    val movieViewModel: MovieViewModel = hiltViewModel()
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val movie by movieViewModel.movie.collectAsState()
    val favorites by profileViewModel.favorites.collectAsState()
    val nunito = FontFamily(Font(R.font.nunito_black))
    var isFavorite by remember { mutableStateOf(false) }
    var isFullScreenTrailer by remember { mutableStateOf(false) }
    var youtubeVideo by remember { mutableStateOf(movie?.videos?.find { it.site == "YouTube" && it.type == "Trailer" }) }
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(movieId) {
        movieViewModel.getMovie(movieId)
    }

    LaunchedEffect(favorites) {
        isFavorite = favorites.contains(movieId.toString())
    }

    LaunchedEffect(movie) {
        youtubeVideo = movie?.videos?.find { it.site == "YouTube" && it.type == "Trailer" }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftDarkBlue)
    ) {
        if (movie == null) {
            CircularLoadingScreen()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = if (isFullScreenTrailer) Modifier
                        .fillMaxSize()
                        .clickable { isFullScreenTrailer = false }
                        .background(Color.Black.copy(0.9f)) else Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f)
                        .background(Color.Black.copy(0.9f))
                ) {
                    if (youtubeVideo != null) {
                        AndroidView(
                            factory = { context ->
                                YouTubePlayerView(context).apply {
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.MATCH_PARENT
                                    )
                                    lifecycleOwner.lifecycle.addObserver(this)
                                    addYouTubePlayerListener(object :
                                        AbstractYouTubePlayerListener() {
                                        override fun onReady(youTubePlayer: YouTubePlayer) {
                                            youTubePlayer.loadVideo(youtubeVideo!!.key, 0f)
                                        }
                                    })
                                }
                            },
                            update = { },
                            modifier = if (isFullScreenTrailer) Modifier
                                .fillMaxWidth()
                                .height(LocalConfiguration.current.screenHeightDp.dp) else Modifier
                                .fillMaxWidth()
                                .aspectRatio(16 / 9f)
                        )
                    } else {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(movie?.movie?.getFullPosterUrl())
                                .crossfade(true)
                                .build(),
                            contentDescription = movie?.movie?.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Transparent,
                                        SoftBlack
                                    ),
                                    startY = 100f
                                )
                            )
                            .clickable { isFullScreenTrailer = true }
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        SoftBlack,
                                        Color.Transparent,
                                        Color.Transparent,
                                        Color.Transparent
                                    ),
                                    startY = 100f
                                )
                            )
                    )

                    IconButton(
                        onClick = {
                            if (isFullScreenTrailer) {
                                isFullScreenTrailer = false
                            } else {
                                navController.navigateUp()
                            }
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .size(40.dp)
                            .offset(y = 48.dp)
                            .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                    ) {
                        val icon =
                            if (isFullScreenTrailer) Icons.Default.KeyboardArrowUp else Icons.AutoMirrored.Filled.ArrowBack

                        Icon(
                            imageVector = icon,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }

                    if (!isFullScreenTrailer) {
                        IconButton(
                            onClick = {
                                if (isFavorite) {
                                    profileViewModel.removeFavorite(movieId.toString())
                                } else {
                                    profileViewModel.addFavorite(movieId.toString())
                                }
                                isFavorite = !isFavorite
                            },
                            modifier = Modifier
                                .padding(16.dp)
                                .size(40.dp)
                                .offset(y = 48.dp)
                                .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                                .align(Alignment.TopEnd)
                        ) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                                tint = if (isFavorite) SoftRed else Color.White
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(movie?.movie?.getFullPosterUrl())
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(140.dp)
                                .aspectRatio(0.7f)
                                .offset(y = (-56).dp)
                                .padding(start = 16.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { }
                        )

                        Column(
                            horizontalAlignment = Alignment.End
                        ) {
                            RatingBar(
                                rating = movie?.movie?.vote_average ?: 0f
                            )
                            movie?.movie?.release_date?.take(4)?.let { year ->
                                ChipInfo(
                                    icon = Icons.Default.DateRange,
                                    text = year,
                                    nunito = nunito
                                )
                            }
                        }
                    }
                    Text(
                        text = movie?.movie?.title ?: "",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontFamily = nunito,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = movie?.movie?.overview ?: "",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = nunito,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    if (!movie?.casts.isNullOrEmpty()) {
                        Text(
                            text = "Cast",
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
                            items(movie?.casts ?: emptyList()) { cast ->
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
        }
    }
}