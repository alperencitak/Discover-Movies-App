package com.alperencitak.discover_movies_app.data.remote.dto

import com.alperencitak.discover_movies_app.domain.model.Genre

data class GenreResponse(
    val genres: List<Genre>
)