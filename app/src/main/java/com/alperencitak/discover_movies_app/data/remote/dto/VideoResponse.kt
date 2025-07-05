package com.alperencitak.discover_movies_app.data.remote.dto

import com.alperencitak.discover_movies_app.domain.model.Video

data class VideoResponse(
    val results: List<Video>
)
