package com.naufalprakoso.moviesjetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteMovieEntity
import com.naufalprakoso.moviesjetpack.vo.Resource
import androidx.paging.PagedList
import com.naufalprakoso.moviesjetpack.utils.Const

open class MovieViewModel(
    private val movieRepository: MovieRepository? = null
) : ViewModel() {

    private val login = MutableLiveData<String>()

    open fun getMoviesPaged(): LiveData<Resource<PagedList<MovieEntity>>>? =
        movieRepository?.getMoviesPaged(Const.API)

    fun getFavoriteMoviesPaged(): LiveData<Resource<PagedList<FavoriteMovieEntity>>>? =
        movieRepository?.getFavoriteMoviesPaged()

    open fun setUsername(username: String) {
        login.value = username
    }
}