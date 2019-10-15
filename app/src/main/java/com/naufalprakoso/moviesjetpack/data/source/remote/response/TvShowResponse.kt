package com.naufalprakoso.moviesjetpack.data.source.remote.response

data class TvShowResponse(
    var id: Int = 0,
    val name: String = "",
    val overview: String = "",
    val vote_average: Double = 0.0,
    val poster_path: String = "",
    val first_air_date: String = "",
    val vote_count: Int = 0
)