package com.naufalprakoso.moviesjetpack.data.source

import androidx.lifecycle.LiveData
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteMovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteTvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.vo.Resource
import androidx.paging.PagedList



interface MovieDataSource {
    fun getMovie(movieId: Int, api: String): LiveData<Resource<MovieEntity>>?
    fun getTvShow(tvShowId: Int, api: String): LiveData<Resource<TvShowEntity>>?

    // Favorite
    fun getFavoriteMovie(movieId: Int): LiveData<Resource<FavoriteMovieEntity>>?
    fun getFavoriteTvShow(tvShowId: Int): LiveData<Resource<FavoriteTvShowEntity>>?

    fun setFavoriteMovie(movie: FavoriteMovieEntity?)
    fun setFavoriteTvShow(tvShow: FavoriteTvShowEntity?)

    fun unsetFavoriteMovie(movie: FavoriteMovieEntity?)
    fun unsetFavoriteTvShow(tvShow: FavoriteTvShowEntity?)

    fun checkFavoriteMovieState(movieId: Int): LiveData<Resource<List<FavoriteMovieEntity>>>
    fun checkFavoriteTvShowState(tvShowId: Int): LiveData<Resource<List<FavoriteTvShowEntity>>>

    // Paging
    fun getMoviesPaged(api: String): LiveData<Resource<PagedList<MovieEntity>>>
    fun getTvShowsPaged(api: String): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getFavoriteMoviesPaged(): LiveData<Resource<PagedList<FavoriteMovieEntity>>>
    fun getFavoriteTvShowsPaged(): LiveData<Resource<PagedList<FavoriteTvShowEntity>>>
}