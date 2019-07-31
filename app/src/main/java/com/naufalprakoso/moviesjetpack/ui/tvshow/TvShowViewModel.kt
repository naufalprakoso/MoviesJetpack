package com.naufalprakoso.moviesjetpack.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity

class TvShowViewModel(
    private val movieRepository: MovieRepository? = null
) : ViewModel() {
    fun getTvShows(): LiveData<List<TvShowEntity>>? {
        return movieRepository?.allTvShows()
    }
}