package com.alperencitak.discover_movies_app.view

//import MovieCategoryScreen
//import androidx.compose.animation.AnimatedContentTransitionScope
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavController
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//
//@Composable
//fun NavScreen(navController: NavHostController, paddingValues: PaddingValues) {
//    NavHost(
//        navController = navController,
//        startDestination = "intro",
//        modifier = Modifier.fillMaxSize()
//    ) {
//        composable("intro"){
//            IntroScreen(navController = navController)
//        }
//        composable("main"){
//            MovieListScreen(navController = navController)
//        }
//        composable("search"){
//            MovieSearchScreen(navController = navController)
//        }
//        composable("categories") {
//            MovieCategoryScreen(navController = navController)
//        }
//        composable("movie_detail/{movieId}") { backStackEntry ->
//            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull() ?: 0
//            MovieDetailScreen(navController = navController, movieId = movieId)
//        }
//        composable("category_see_all/{genreId}/{genreName}") { backStackEntry ->
//            val genreId = backStackEntry.arguments?.getString("genreId")?.toIntOrNull() ?: 0
//            val genreName = backStackEntry.arguments?.getString("genreName") ?: ""
//            CategorySeeAllScreen(navController=navController, genreId=genreId, genreName=genreName)
//        }
//        composable("favorites") {
//            FavoriteMoviesScreen(navController=navController, paddingValues)
//        }
//    }
//}