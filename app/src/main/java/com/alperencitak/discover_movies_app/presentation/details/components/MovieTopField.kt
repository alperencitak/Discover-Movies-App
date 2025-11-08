package com.alperencitak.discover_movies_app.presentation.details.components

import android.util.Log
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.domain.model.Video
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.ui.theme.SoftRed
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun MovieTrailerAndPoster(
    movie: Movie?,
    trailer: Video?,
    onFavoriteClick: () -> Unit,
    isFavorite: Boolean,
    navigateUp: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var isFullScreenTrailer by remember { mutableStateOf(false) }

    Box(
        modifier = if (isFullScreenTrailer) Modifier
            .fillMaxSize()
            .clickable { isFullScreenTrailer = false }
            .background(Color.Black.copy(0.9f)) else Modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f)
            .background(Color.Black.copy(0.9f))
    ) {
        if (trailer != null) {
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
                                youTubePlayer.loadVideo(trailer.key, 0f)
                            }
                            override fun onError(player: YouTubePlayer, error: PlayerConstants.PlayerError) {
                                Log.e("YT", "Error: $error")
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
                    .data(movie?.getFullPosterUrl())
                    .crossfade(true)
                    .build(),
                contentDescription = movie?.title,
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
                        startY = 75f
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
                    navigateUp()
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
                    onFavoriteClick()
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
}