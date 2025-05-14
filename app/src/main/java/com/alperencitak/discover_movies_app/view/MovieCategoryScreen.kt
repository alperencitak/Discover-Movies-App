import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.utils.CategorySection
import com.alperencitak.discover_movies_app.utils.CircularLoadingScreen
import com.alperencitak.discover_movies_app.viewmodel.MovieViewModel

@Composable
fun MovieCategoryScreen(navController: NavHostController) {
    val moviesViewModel: MovieViewModel = hiltViewModel()
    val moviesByGenre by moviesViewModel.moviesByGenre.collectAsState()
    val topRatedMovies by moviesViewModel.topRatedMovies.collectAsState()
    val trendingMoviesWeek by moviesViewModel.trendingMovies.collectAsState()
    val genres by moviesViewModel.genres.collectAsState()
    val genreList = listOf(28, 878, 16, 35, 27, 14, 80, 12, 18, 10751, 9648, 10749, 53, 10752)
    val nunito = FontFamily(Font(R.font.nunito_black))

    LaunchedEffect(Unit) {
        moviesViewModel.getMovieGenres()
        moviesViewModel.getTopRatedMovies(1)
        moviesViewModel.getTrendingMovies(1, "week")
        genreList.forEach { genreId ->
            moviesViewModel.getMoviesByGenre(1, genreId, expandable = false)
        }
    }

    if (moviesByGenre.isEmpty()) {
        CircularLoadingScreen()
    } else {
        Box(
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Top Rated
                CategorySection(
                    title = "Top Rated Movies",
                    movies = topRatedMovies,
                    onSeeAllClick = {},
                    onMovieClick = { movieId ->
                        navController.navigate("movie_detail/$movieId")
                    },
                    nunito = nunito
                )

                // Trending
                CategorySection(
                    title = "Trending This Week",
                    movies = trendingMoviesWeek,
                    onSeeAllClick = {},
                    onMovieClick = { movieId ->
                        navController.navigate("movie_detail/$movieId")
                    },
                    nunito = nunito
                )

                // Movies by genre
                genreList.forEach { genreId ->
                    val genreName = genres.find { it.id == genreId }?.name ?: return@forEach
                    val moviesInGenre = moviesByGenre[genreId] ?: return@forEach

                    if (moviesInGenre.isNotEmpty()) {
                        CategorySection(
                            title = genreName,
                            movies = moviesInGenre,
                            onSeeAllClick = {
                                navController.navigate("category_see_all/$genreId/$genreName")
                            },
                            onMovieClick = { movieId ->
                                navController.navigate("movie_detail/$movieId")
                            },
                            nunito = nunito
                        )
                    }
                }

                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}