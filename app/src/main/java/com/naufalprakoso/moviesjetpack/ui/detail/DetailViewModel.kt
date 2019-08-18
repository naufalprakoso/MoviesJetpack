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

    fun getFavoriteMovie(): LiveData<Resource<FavoriteMovieEntity>> =
        Transformations.switchMap(login) { movieRepository?.getFavoriteMovie(movieId) }

    fun getFavoriteTvShow(): LiveData<Resource<FavoriteTvShowEntity>> =
        Transformations.switchMap(login) { movieRepository?.getFavoriteTvShow(movieId) }

    fun checkFavoriteMoviesState(): LiveData<Resource<List<FavoriteMovieEntity>>> =
        Transformations.switchMap(login) { movieId?.let { it1 -> movieRepository?.checkFavoriteMovieState(it1) } }

    fun checkFavoriteTvShowsState(): LiveData<Resource<List<FavoriteTvShowEntity>>> =
        Transformations.switchMap(login) { movieId?.let { it1 -> movieRepository?.checkFavoriteTvShowState(it1) } }

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