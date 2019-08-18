package com.naufalprakoso.moviesjetpack.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteMovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteTvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.vo.Resource

class DetailViewModel(
    var movieId: String? = "",
    private val movieRepository: MovieRepository? = null
) : ViewModel() {

    private val login = MutableLiveData<String>()

    fun getMovie(): LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(login) { movieRepository?.getMovie(movieId) }

    fun getTvShow(): LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(login) { movieRepository?.getTvShow(movieId) }

    fun setFavoriteMovie(movieEntity: FavoriteMovieEntity?) {
        movieRepository?.setFavoriteMovie(movieEntity)
    }

    fun setFavoriteTvShow(tvShowEntity: FavoriteTvShowEntity?) {
        movieRepository?.setFavoriteTvShow(tvShowEntity)
    }

    fun setUsername(username: String) {
        login.value = username
    }
}