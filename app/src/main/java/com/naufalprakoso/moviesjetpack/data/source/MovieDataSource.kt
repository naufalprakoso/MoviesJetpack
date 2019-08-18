package com.naufalprakoso.moviesjetpack.data.source

import androidx.lifecycle.LiveData
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteMovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteTvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.vo.Resource

interface MovieDataSource {
    fun allMovies(): LiveData<Resource<List<MovieEntity>>>
    fun allTvShows(): LiveData<Resource<List<TvShowEntity>>>

    fun allFavoriteMovies(): LiveData<Resource<List<MovieEntity>>>
    fun allFavoriteTvShows(): LiveData<Resource<List<TvShowEntity>>>

    fun getMovie(movieId: String?): LiveData<Resource<MovieEntity>>?
    fun getTvShow(tvShowId: String?): LiveData<Resource<TvShowEntity>>?

    fun getFavoriteMovie(movieId: String?): LiveData<Resource<MovieEntity>>?
    fun getFavoriteTvShow(tvShowId: String?): LiveData<Resource<TvShowEntity>>?

    fun setFavoriteMovie(movie: FavoriteMovieEntity?)
    fun setFavoriteTvShow(tvShow: FavoriteTvShowEntity?)

    fun unsetFavoriteMovie(movie: FavoriteMovieEntity?)
    fun unsetFavoriteTvShow(tvShow: FavoriteTvShowEntity?)
}