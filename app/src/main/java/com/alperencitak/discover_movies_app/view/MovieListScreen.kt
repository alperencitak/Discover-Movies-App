package com.alperencitak.discover_movies_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.alperencitak.discover_movies_app.model.Movie
import com.alperencitak.discover_movies_app.viewmodel.MovieViewModel

@Composable
fun MovieListScreen() {
    val movieViewModel: MovieViewModel = hiltViewModel()
    val movies by movieViewModel.movies.collectAsState()
    var page = 1
    Column(
        modifier = Modifier.padding(horizontal = 8.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth().height(720.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie = movie, onClick = { println(movie.title) })
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    if(page>1){
                        page-=1
                        movieViewModel.getMovies(page)
                    }
                }
            ) { Text(text = "<-") }
            Button(
                onClick = {
                    page+=1
                    movieViewModel.getMovies(page)
                }
            ) { Text(text = "->") }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = movie.getFullPosterUrl()),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Movie Poster",
            modifier = Modifier.fillMaxSize().height(300.dp)
        )
        Row(
            modifier = Modifier.clickable { onClick() }.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = movie.title,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp),
                minLines = 2
            )
        }
    }
}
