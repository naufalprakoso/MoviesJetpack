package com.naufalprakoso.moviesjetpack.data.source.remote.response

data class MovieResponse(
    var id: Int = 0,
    val title: String = "",
    val overview: String = "",
    val vote_average: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val vote_count: Int = 0
)