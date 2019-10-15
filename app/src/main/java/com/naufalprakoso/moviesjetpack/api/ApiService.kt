package com.naufalprakoso.moviesjetpack.api

import androidx.lifecycle.LiveData
import com.naufalprakoso.moviesjetpack.data.source.remote.ApiResponse
import com.naufalprakoso.moviesjetpack.data.source.remote.response.MovieJSONResponse
import com.naufalprakoso.moviesjetpack.data.source.remote.response.MovieResponse
import com.naufalprakoso.moviesjetpack.data.source.remote.response.TvShowJSONResponse
import com.naufalprakoso.moviesjetpack.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun getAllMovies(
        @Query("api_key") api: String
    ): LiveData<ApiResponse<MovieJSONResponse>>

    @GET("tv/popular")
    fun getAllTvShows(
        @Query("api_key") api: String
    ): LiveData<ApiResponse<TvShowJSONResponse>>

    @GET("https://api.themoviedb.org/3/movie/{movieId}")
    fun getMovie(
        @Path("movieId") movieId: String,
        @Query("api_key") api: String
    ): LiveData<ApiResponse<MovieResponse>>

    @GET("https://api.themoviedb.org/3/tv/{tvId}")
    fun getTvShow(
        @Path("tvId") tvShowId: String,
        @Query("api_key") api: String
    ): LiveData<ApiResponse<TvShowResponse>>
}