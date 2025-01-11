package com.alperencitak.discover_movies_app.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.ui.theme.SoftRed
import com.alperencitak.discover_movies_app.utils.MovieItem
import com.alperencitak.discover_movies_app.utils.CircularLoadingScreen
import com.alperencitak.discover_movies_app.viewmodel.MovieViewModel

@Composable
fun CategorySeeAllScreen(navController: NavHostController, genreId: Int, genreName: String) {
    val movieViewModel: MovieViewModel = hiltViewModel()
    val moviesByGenre by movieViewModel.moviesByGenre.collectAsState()
    var isLoadingMore by remember { mutableStateOf(false) }
    var currentPage by remember { mutableIntStateOf(1) }

    LaunchedEffect(currentPage) {
        if(!isLoadingMore){
            isLoadingMore = true
            movieViewModel.getMoviesByGenre(page = currentPage, genreId=genreId)
            isLoadingMore = false
        }
    }

    val nunito = FontFamily(
        Font(R.font.nunito_black)
    )

    if(moviesByGenre.isNotEmpty()){
        val movies = moviesByGenre[genreId] ?: emptyList()
        if(movies.isNotEmpty()){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(SoftBlack)
            )
            Text(
                text = genreName,
                fontFamily = nunito,
                fontWeight = FontWeight.Bold,
                color = SoftRed,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth().padding(top = 64.dp),
                textAlign = TextAlign.Center
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp, bottom = 32.dp, top = 96.dp),
            ) {
                items(movies){ movie ->
                    MovieItem(movie) {
                        navController.navigate("movie_detail/${movie.id}")
                    }
                }

                item{
                    if(!isLoadingMore){
                        LaunchedEffect(Unit) {
                            currentPage++
                        }
                    }
                }
            }
        }else{
            CircularLoadingScreen()
        }
    }else{
        CircularLoadingScreen()
    }
}
