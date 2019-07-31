package com.naufalprakoso.moviesjetpack.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity

class DetailViewModel(
    var movieId: String? = "",
    private val movieRepository: MovieRepository? = null
) : ViewModel() {
    fun getMovie(): LiveData<MovieEntity>? {
        return movieRepository?.getMovie(movieId)
    }

    fun getTvShow(): LiveData<TvShowEntity>? {
        return movieRepository?.getTvShow(movieId)
    }
}