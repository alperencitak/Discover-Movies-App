package com.alperencitak.discover_movies_app.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.ui.theme.SoftRed
import com.alperencitak.discover_movies_app.utils.CategoryMovieCard
import com.alperencitak.discover_movies_app.utils.CircularLoadingScreen
import com.alperencitak.discover_movies_app.utils.LoadingIndicator
import com.alperencitak.discover_movies_app.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySeeAllScreen(
    navController: NavHostController,
    genreId: Int,
    genreName: String
) {
    val movieViewModel: MovieViewModel = hiltViewModel()
    val moviesByGenre by movieViewModel.moviesByGenre.collectAsState()
    var isLoadingMore by remember { mutableStateOf(false) }
    var currentPage by remember { mutableIntStateOf(1) }
    val nunito = FontFamily(Font(R.font.nunito_black))

    LaunchedEffect(currentPage) {
        if(!isLoadingMore){
            isLoadingMore = true
            movieViewModel.getMoviesByGenre(page = currentPage, genreId=genreId, expandable = true)
            isLoadingMore = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1F1D2B),
                        Color(0xFF171621)
                    )
                )
            )
    ) {
        TopAppBar(
            title = {
                Text(
                    text = genreName,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = nunito,
                        fontWeight = FontWeight.Bold,
                        color = SoftRed,
                        fontSize = 24.sp
                    )
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = SoftRed
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier.statusBarsPadding()
        )

        if(moviesByGenre.isEmpty()) {
            CircularLoadingScreen()
        } else {
            val movies = moviesByGenre[genreId] ?: emptyList()

            if(movies.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 24.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(movies.size) { index ->
                        CategoryMovieCard(
                            movie = movies[index],
                            onClick = {
                                navController.navigate("movie_detail/${movies[index].id}")
                            },
                            nunito = nunito)

                        if (index == movies.size - 1 && !isLoadingMore) {
                            LaunchedEffect(Unit) {
                                currentPage++
                            }
                        }
                    }

                    if (isLoadingMore) {
                        item(span = { GridItemSpan(2) }) {
                            LoadingIndicator()
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "No movies found",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = nunito,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            ),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Try checking back later for new movies in this category",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = nunito,
                                color = Color.White.copy(alpha = 0.7f)
                            ),
                            textAlign = TextAlign.Center
                        )
                        Button(
                            onClick = { navController.navigateUp() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SoftRed
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Go Back",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontFamily = nunito,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
