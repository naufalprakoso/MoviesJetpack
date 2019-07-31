package com.naufalprakoso.moviesjetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity

class MovieViewModel(
    private val movieRepository: MovieRepository? = null
) : ViewModel() {
    fun getMovies(): LiveData<List<MovieEntity>>? {
        return movieRepository?.allMovies()
    }
}