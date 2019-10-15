package com.naufalprakoso.moviesjetpack.data.source.remote.response

data class MovieJSONResponse(
    var page: Int = 0,
    var total_results: Int = 0,
    var total_pages: Int = 0,
    var results: List<MovieResponse>
)