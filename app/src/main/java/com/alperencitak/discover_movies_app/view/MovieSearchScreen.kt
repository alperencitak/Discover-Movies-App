package com.alperencitak.discover_movies_app.view

//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.heightIn
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.GridItemSpan
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Clear
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavHostController
//import com.alperencitak.discover_movies_app.R
//import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
//import com.alperencitak.discover_movies_app.ui.theme.SoftRed
//import com.alperencitak.discover_movies_app.utils.EmptySearchState
//import com.alperencitak.discover_movies_app.utils.LoadingIndicator
//import com.alperencitak.discover_movies_app.utils.SearchResultCard
//import com.alperencitak.discover_movies_app.viewmodel.MovieViewModel
//
//@Composable
//fun MovieSearchScreen(navController: NavHostController) {
//    val movieViewModel: MovieViewModel = hiltViewModel()
//    val movies by movieViewModel.searchedMovies.collectAsState()
//    var currentPage by remember { mutableIntStateOf(1) }
//    var isLoadingMore by remember { mutableStateOf(false) }
//    var searchQuery by remember { mutableStateOf("") }
//    val nunito = FontFamily(Font(R.font.nunito_black))
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(SoftBlack)
//    ) {
//        Column(
//            modifier = Modifier.fillMaxSize().padding(top = 36.dp)
//        ) {
//            Surface(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                shape = RoundedCornerShape(12.dp),
//                color = Color(0xFF252836)
//            ) {
//                TextField(
//                    value = searchQuery,
//                    onValueChange = { newQuery ->
//                        searchQuery = newQuery
//                        currentPage = 1
//                        if (newQuery.isNotEmpty()) {
//                            movieViewModel.searchMovie(newQuery, currentPage)
//                        }
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .heightIn(min = 56.dp),
//                    placeholder = {
//                        Text(
//                            "Search for movies...",
//                            fontFamily = nunito,
//                            color = Color.White.copy(alpha = 0.6f)
//                        )
//                    },
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.Search,
//                            contentDescription = "Search",
//                            tint = Color.White
//                        )
//                    },
//                    trailingIcon = {
//                        if (searchQuery.isNotEmpty()) {
//                            IconButton(
//                                onClick = {
//                                    searchQuery = ""
//                                    movieViewModel.searchMovie("", 1)
//                                }
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.Clear,
//                                    contentDescription = "Clear",
//                                    tint = Color.White
//                                )
//                            }
//                        }
//                    },
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        disabledContainerColor = Color.Transparent,
//                        cursorColor = SoftRed,
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent
//                    ),
//                    textStyle = TextStyle(
//                        fontFamily = nunito,
//                        fontSize = 16.sp
//                    ),
//                    singleLine = true,
//                    keyboardOptions = KeyboardOptions(
//                        imeAction = ImeAction.Search
//                    ),
//                    keyboardActions = KeyboardActions(
//                        onSearch = {
//                            if (searchQuery.isNotEmpty()) {
//                                movieViewModel.searchMovie(searchQuery, currentPage)
//                            }
//                        }
//                    )
//                )
//            }
//
//            if (movies.isEmpty() && searchQuery.isEmpty()) {
//                EmptySearchState(nunito)
//            } else {
//                LazyVerticalGrid(
//                    columns = GridCells.Fixed(2),
//                    contentPadding = PaddingValues(16.dp),
//                    horizontalArrangement = Arrangement.spacedBy(12.dp),
//                    verticalArrangement = Arrangement.spacedBy(16.dp),
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    items(movies.size) { index ->
//                        if (index >= movies.size - 4 && !isLoadingMore && searchQuery.isNotEmpty()) {
//                            LaunchedEffect(Unit) {
//                                isLoadingMore = true
//                                currentPage++
//                                movieViewModel.searchMovie(searchQuery, currentPage)
//                                isLoadingMore = false
//                            }
//                        }
//                        SearchResultCard(
//                            movie = movies[index],
//                            nunito = nunito,
//                            onClick = {
//                                navController.navigate("movie_detail/${movies[index].id}")
//                            }
//                        )
//                    }
//
//                    if (isLoadingMore) {
//                        item(span = { GridItemSpan(2) }) {
//                            LoadingIndicator()
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
