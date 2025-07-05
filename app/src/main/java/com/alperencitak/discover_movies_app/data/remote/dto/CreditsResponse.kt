package com.alperencitak.discover_movies_app.data.remote.dto

import com.alperencitak.discover_movies_app.domain.model.Cast
import com.alperencitak.discover_movies_app.domain.model.Crew

data class CreditsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>
)
