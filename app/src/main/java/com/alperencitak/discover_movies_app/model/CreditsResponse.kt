package com.alperencitak.discover_movies_app.model

data class CreditsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>
)
