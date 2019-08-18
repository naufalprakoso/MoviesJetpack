package com.naufalprakoso.moviesjetpack.data.source

import androidx.lifecycle.LiveData
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteMovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteTvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.vo.Resource
import androidx.paging.PagedList



interface MovieDataSource {
    @Deprecated("This method not implemented a pagination")
    fun allMovies(): LiveData<Resource<List<MovieEntity>>>
    @Deprecated("This method not implemented a pagination")
    fun allTvShows(): LiveData<Resource<List<TvShowEntity>>>

    @Deprecated("This method not implemented a pagination")
    fun allFavoriteMovies(): LiveData<Resource<List<FavoriteMovieEntity>>>
    @Deprecated("This method not implemented a pagination")
    fun allFavoriteTvShows(): LiveData<Resource<List<FavoriteTvShowEntity>>>

    fun getMovie(movieId: String?): LiveData<Resource<MovieEntity>>?
    fun getTvShow(tvShowId: String?): LiveData<Resource<TvShowEntity>>?

    // Favorite
    fun getFavoriteMovie(movieId: String?): LiveData<Resource<FavoriteMovieEntity>>?
    fun getFavoriteTvShow(tvShowId: String?): LiveData<Resource<FavoriteTvShowEntity>>?

    fun setFavoriteMovie(movie: FavoriteMovieEntity?)
    fun setFavoriteTvShow(tvShow: FavoriteTvShowEntity?)

    fun unsetFavoriteMovie(movie: FavoriteMovieEntity?)
    fun unsetFavoriteTvShow(tvShow: FavoriteTvShowEntity?)

    fun checkFavoriteMovieState(movieId: String): LiveData<Resource<List<FavoriteMovieEntity>>>
    fun checkFavoriteTvShowState(tvShowId: String): LiveData<Resource<List<FavoriteTvShowEntity>>>

    // Paging
    fun getMoviesPaged(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getTvShowsPaged(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getFavoriteMoviesPaged(): LiveData<Resource<PagedList<FavoriteMovieEntity>>>
    fun getFavoriteTvShowsPaged(): LiveData<Resource<PagedList<FavoriteTvShowEntity>>>
}