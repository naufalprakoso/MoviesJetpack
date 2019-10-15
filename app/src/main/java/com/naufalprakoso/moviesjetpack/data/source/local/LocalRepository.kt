package com.naufalprakoso.moviesjetpack.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteMovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteTvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.local.room.MovieDao

class LocalRepository(
    private val movieDao: MovieDao
) {
    companion object{
        private var INSTANCE: LocalRepository? = null

        fun getInstance(movieDao: MovieDao): LocalRepository? {
            if (INSTANCE == null){
                INSTANCE = LocalRepository(movieDao)
            }

            return INSTANCE
        }
    }

    fun getMovie(movieId: Int): LiveData<MovieEntity>{
        return movieDao.getMovie(movieId)
    }

    fun getTvShow(tvShowId: Int): LiveData<TvShowEntity>{
        return movieDao.getTvShow(tvShowId)
    }

    fun insertMovies(movies: List<MovieEntity>) {
        movieDao.insertMovie(movies)
    }

    fun insertTvShows(tvShows: List<TvShowEntity>) {
        movieDao.insertTvShow(tvShows)
    }

//    Favorite
    fun getFavoriteMovie(movieId: Int): LiveData<FavoriteMovieEntity>{
        return movieDao.getFavoriteMovie(movieId)
    }

    fun getFavoriteTvShow(tvShowId: Int): LiveData<FavoriteTvShowEntity>{
        return movieDao.getFavoriteTvShow(tvShowId)
    }

    fun insertFavoriteMovie(movie: FavoriteMovieEntity) {
        movieDao.insertFavoriteMovie(movie)
    }

    fun insertFavoriteTvShow(tvShow: FavoriteTvShowEntity) {
        movieDao.insertFavoriteTvShow(tvShow)
    }

    fun deleteFavoriteMovie(movie: FavoriteMovieEntity) {
        movieDao.deleteFavoriteMovie(movie)
    }

    fun deleteFavoriteTvShow(tvShow: FavoriteTvShowEntity) {
        movieDao.deleteFavoriteTvShow(tvShow)
    }

    fun checkFavoriteMovieState(movieId: Int): LiveData<List<FavoriteMovieEntity>>{
        return movieDao.checkFavoriteMovieState(movieId)
    }

    fun checkFavoriteTvShowState(tvShowId: Int): LiveData<List<FavoriteTvShowEntity>>{
        return movieDao.checkFavoriteTvShowState(tvShowId)
    }

    // Paging
    fun getAllMoviesPaged(): DataSource.Factory<Int, MovieEntity>{
        return movieDao.getMovieAsPaged()
    }

    fun getAllTvShowsPaged(): DataSource.Factory<Int, TvShowEntity>{
        return movieDao.getTvShowAsPaged()
    }

    fun getAllFavoriteMoviesPaged(): DataSource.Factory<Int, FavoriteMovieEntity>{
        return movieDao.getFavoriteMovieAsPaged()
    }

    fun getAllFavoriteTvShowsPaged(): DataSource.Factory<Int, FavoriteTvShowEntity>{
        return movieDao.getFavoriteTvShowAsPaged()
    }
}