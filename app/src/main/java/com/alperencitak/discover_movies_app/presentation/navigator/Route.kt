package com.alperencitak.discover_movies_app.presentation.navigator

sealed class Route(
    val route: String
) {

    object HomeScreen: Route(route = "home")
    object SearchScreen: Route(route = "search")
    object CategoriesScreen: Route(route = "categories")
    object FavoritesScreen: Route(route = "favorites")
    object DetailsScreen: Route(route = "details")
    object CastMoviesScreen: Route(route = "castmovies")

}