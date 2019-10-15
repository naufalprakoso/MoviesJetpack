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
import com.naufalprakoso.moviesjetpack.utils.Const
import com.naufalprakoso.moviesjetpack.vo.Resource

class DetailViewModel(
    var movieId: Int = 0,
    private val movieRepository: MovieRepository? = null
) : ViewModel() {

    private val login = MutableLiveData<String>()

    fun getMovie(): LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(login) { movieRepository?.getMovie(movieId, Const.API) }

    fun getTvShow(): LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(login) { movieRepository?.getTvShow(movieId, Const.API) }

    fun getFavoriteMovie(): LiveData<Resource<FavoriteMovieEntity>> =
        Transformations.switchMap(login) { movieRepository?.getFavoriteMovie(movieId) }

    fun getFavoriteTvShow(): LiveData<Resource<FavoriteTvShowEntity>> =
        Transformations.switchMap(login) { movieRepository?.getFavoriteTvShow(movieId) }

    fun checkFavoriteMoviesState(): LiveData<Resource<List<FavoriteMovieEntity>>> =
        Transformations.switchMap(login) { movieRepository?.checkFavoriteMovieState(movieId) }

    fun checkFavoriteTvShowsState(): LiveData<Resource<List<FavoriteTvShowEntity>>> =
        Transformations.switchMap(login) { movieRepository?.checkFavoriteTvShowState(movieId) }

    fun setFavoriteMovie(movieEntity: FavoriteMovieEntity?) {
        movieRepository?.setFavoriteMovie(movieEntity)
    }

    fun setFavoriteTvShow(tvShowEntity: FavoriteTvShowEntity?) {
        movieRepository?.setFavoriteTvShow(tvShowEntity)
    }

    fun unsetFavoriteMovie(movieEntity: FavoriteMovieEntity?) {
        movieRepository?.unsetFavoriteMovie(movieEntity)
    }

    fun unsetFavoriteTvShow(tvShowEntity: FavoriteTvShowEntity?) {
        movieRepository?.unsetFavoriteTvShow(tvShowEntity)
    }

    fun setUsername(username: String) {
        login.value = username
    }
}