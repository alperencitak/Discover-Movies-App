package com.alperencitak.discover_movies_app.model

data class MovieWithDetails (
    val movie: Movie,
    val videos: List<Video>,
    val casts: List<Cast>,
    val crews: List<Crew>
)