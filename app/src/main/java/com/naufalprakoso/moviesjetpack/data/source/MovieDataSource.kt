package com.naufalprakoso.moviesjetpack.data.source

import androidx.lifecycle.LiveData
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity

interface MovieDataSource {
    fun allMovies(): LiveData<List<MovieEntity>>
    fun allTvShows(): LiveData<List<TvShowEntity>>

    fun getMovie(movieId: String?): LiveData<MovieEntity>?
    fun getTvShow(tvShowId: String?): LiveData<TvShowEntity>?
}