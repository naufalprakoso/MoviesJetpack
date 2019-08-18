package com.naufalprakoso.moviesjetpack.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteTvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.vo.Resource

class TvShowViewModel(
    private val movieRepository: MovieRepository? = null
) : ViewModel() {

    private val login = MutableLiveData<String>()

    @Deprecated("This method not implemented a pagination")
    fun getTvShows(): LiveData<Resource<List<TvShowEntity>>> =
        Transformations.switchMap(login) { movieRepository?.allTvShows() }

    @Deprecated("This method not implemented a pagination")
    fun getFavoriteTvShows(): LiveData<Resource<List<FavoriteTvShowEntity>>> =
        Transformations.switchMap(login) { movieRepository?.allFavoriteTvShows() }

    fun getTvShowsPaged(): LiveData<Resource<PagedList<TvShowEntity>>>? {
        return movieRepository?.getTvShowsPaged()
    }

    fun getFavoriteTvShowsPaged(): LiveData<Resource<PagedList<FavoriteTvShowEntity>>>? {
        return movieRepository?.getFavoriteTvShowsPaged()
    }

    fun setUsername(username: String) {
        login.value = username
    }
}