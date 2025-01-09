package com.alperencitak.discover_movies_app.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.alperencitak.discover_movies_app.utils.MovieItem
import com.alperencitak.discover_movies_app.utils.CircularLoadingScreen
import com.alperencitak.discover_movies_app.viewmodel.MovieViewModel

@Composable
fun CategorySeeAllScreen(navController: NavHostController, genreId: Int, genreName: String) {
    val movieViewModel: MovieViewModel = hiltViewModel()
    val moviesByGenre by movieViewModel.movies.collectAsState()
    movieViewModel.getMoviesByGenre(page = 1, genreId=genreId)

    val nunito = FontFamily(
        Font(R.font.nunito_black)
    )

    if(moviesByGenre.isNotEmpty()){
        Text(
            text = genreName,
            fontFamily = nunito,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            items(moviesByGenre){ movie ->
                MovieItem(movie) {
                    navController.navigate("movie_detail/${movie.id}")
                }
            }
        }
    }else{
        CircularLoadingScreen()
    }
}
