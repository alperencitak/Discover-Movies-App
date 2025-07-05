package com.alperencitak.discover_movies_app.data.remote.dto

import com.alperencitak.discover_movies_app.domain.model.Cast
import com.alperencitak.discover_movies_app.domain.model.Crew
import com.alperencitak.discover_movies_app.domain.model.Movie
import com.alperencitak.discover_movies_app.domain.model.Video

data class MovieResponse (
    val movie: Movie,
    val videos: List<Video>,
    val casts: List<Cast>,
    val crews: List<Crew>
)