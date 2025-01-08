package com.alperencitak.discover_movies_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.model.Movie
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.ui.theme.SoftRed
import com.alperencitak.discover_movies_app.viewmodel.MovieViewModel

@Composable
fun MovieCategoryScreen(navController: NavHostController) {

    val moviesViewModel: MovieViewModel = hiltViewModel()
    val moviesByGenre by moviesViewModel.moviesByGenre.collectAsState()
    val genres by moviesViewModel.genres.collectAsState()
    val genreList = listOf(28, 878, 16, 35, 27, 14, 80, 12, 18, 10751, 9648, 10749, 53, 10752)

    moviesViewModel.getMovieGenres()
    genreList.forEach { genreId ->
        moviesViewModel.getMoviesByGenre(1, genreId)
    }

    Box(
        modifier = Modifier.fillMaxSize().background(SoftBlack)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        genreList.forEach { genreId ->
            val movies = moviesByGenre[genreId] ?: emptyList()
            val genreName = genres.find{ it.id == genreId}?.name ?: ""
            ListRow(genreName, movies){ movieId ->
                navController.navigate("movie_detail_screen/$movieId")
            }
        }
    }

}

@Composable
fun ListRow(title: String, movies: List<Movie>, onClick: (Int) -> Unit){
    val nunito = FontFamily(
        Font(R.font.nunito_black)
    )
    Column(
        modifier = Modifier.fillMaxWidth().height(300.dp).padding(horizontal = 16.dp)
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
                text = "See All",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = SoftRed,
                fontFamily = nunito
            )
        }
        LazyRow (
            modifier = Modifier.fillMaxHeight()
        ) {
            items(movies){ movie ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(200.dp)
                        .padding(horizontal = 4.dp, vertical = 8.dp)
                        .clickable {
                        onClick(movie.id)
                    }
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = movie.getFullPosterUrl()),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Movie Poster",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}