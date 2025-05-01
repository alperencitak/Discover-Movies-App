package com.alperencitak.discover_movies_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.ui.theme.SoftGray
import com.alperencitak.discover_movies_app.utils.CircularLoadingScreen
import com.alperencitak.discover_movies_app.utils.FavoritesLoadingScreen
import com.alperencitak.discover_movies_app.utils.getVoteColor
import com.alperencitak.discover_movies_app.viewmodel.MovieViewModel
import com.alperencitak.discover_movies_app.viewmodel.ProfileViewModel
import kotlinx.coroutines.delay
import java.util.Locale

@Composable
fun FavoriteMoviesScreen(navController: NavHostController) {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val movieViewModel: MovieViewModel = hiltViewModel()
    val favoriteIds by profileViewModel.favorites.collectAsState()
    val movies by movieViewModel.favoriteMovies.collectAsState()
    val nunito = FontFamily(
        Font(R.font.nunito_black)
    )
    var isLoading by remember { mutableStateOf(true) }
    profileViewModel.loadFavorite()
    LaunchedEffect(favoriteIds) {
        movieViewModel.getFavoriteMovies(favoriteIds)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftBlack)
    )

    LaunchedEffect(Unit) {
        delay(2500)
        isLoading = false
    }

    if(!isLoading){
        if (movies.isNotEmpty()) {
            Spacer(modifier = Modifier.height(56.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }
                items(movies) { movie ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(3f / 2f)
                            .padding(vertical = 8.dp)
                            .clickable {
                                navController.navigate("movie_detail/${movie.id}")
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(model = movie.getFullPosterUrl()),
                                contentDescription = "Movie Poster",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .aspectRatio(2.5f / 3f)
                                    .padding(end = 16.dp)
                                    .scale(0.8f)
                                    .background(SoftBlack)
                            )
                            Column(
                                modifier = Modifier.padding(vertical = 32.dp)
                            ) {
                                Text(
                                    fontSize = 19.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = nunito,
                                    text = movie.title,
                                    color = SoftGray
                                )
                                Button(
                                    onClick = { },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = getVoteColor(movie.vote_average)
                                    ),
                                    shape = androidx.compose.foundation.shape.CircleShape,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                ) {
                                    Text(
                                        text = String.format(Locale.US, "%.2f", movie.vote_average),
                                        color = SoftBlack,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = nunito
                                    )
                                }
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(48.dp))
                }
            }
        }else{
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = nunito,
                    text = "Your favorite list is empty",
                    color = SoftGray,
                    modifier = Modifier.padding(top = 56.dp)
                )
            }
        }
    }else{
        FavoritesLoadingScreen()
    }
}