package com.naufalprakoso.moviesjetpack.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteTvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.utils.Const
import com.naufalprakoso.moviesjetpack.vo.Resource

open class TvShowViewModel(
    private val movieRepository: MovieRepository? = null
) : ViewModel() {

    private val login = MutableLiveData<String>()

    open fun getTvShowsPaged(): LiveData<Resource<PagedList<TvShowEntity>>>? =
        movieRepository?.getTvShowsPaged(Const.API)

    fun getFavoriteTvShowsPaged(): LiveData<Resource<PagedList<FavoriteTvShowEntity>>>? =
        movieRepository?.getFavoriteTvShowsPaged()

    open fun setUsername(username: String) {
        login.value = username
    }
}