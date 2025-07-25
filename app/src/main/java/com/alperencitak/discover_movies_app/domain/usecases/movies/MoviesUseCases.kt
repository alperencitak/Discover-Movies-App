package com.alperencitak.discover_movies_app.domain.usecases.movies

data class MoviesUseCases(
    val getMovies: GetMovies,
    val getTopRatedMovies: GetTopRatedMovies,
    val searchMovies: SearchMovies,
    val getMoviesByGenre: GetMoviesByGenre,
    val getMovie: GetMovie,
    val getGenres: GetGenres,
    val upsertMovie: UpsertMovie,
    val deleteMovie: DeleteMovie,
    val selectMovies: SelectMovies,
    val selectMovie: SelectMovie,
    val isMovieFavorite: IsMovieFavorite
)
