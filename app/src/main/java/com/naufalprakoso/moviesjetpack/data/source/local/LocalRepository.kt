package com.naufalprakoso.moviesjetpack.data.source.local

import androidx.lifecycle.LiveData
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

    fun getAllMovies(): LiveData<List<MovieEntity>>{
        return movieDao.getMovies()
    }

    fun getAllTvShows(): LiveData<List<TvShowEntity>>{
        return movieDao.getTvShows()
    }

    fun getMovie(movieId: String): LiveData<MovieEntity>{
        return movieDao.getMovie(movieId)
    }

    fun getTvShow(tvShowId: String): LiveData<TvShowEntity>{
        return movieDao.getTvShow(tvShowId)
    }

    fun insertMovies(movies: List<MovieEntity>) {
        movieDao.insertMovie(movies)
    }

    fun insertTvShows(tvShows: List<TvShowEntity>) {
        movieDao.insertTvShow(tvShows)
    }

//    Favorite
    fun getAllFavoriteMovies(): LiveData<List<MovieEntity>>{
        return movieDao.getMovies()
    }

    fun getAllFavoriteTvShows(): LiveData<List<TvShowEntity>>{
        return movieDao.getTvShows()
    }

    fun getFavoriteMovie(movieId: String): LiveData<MovieEntity>{
        return movieDao.getMovie(movieId)
    }

    fun getFavoriteTvShow(tvShowId: String): LiveData<TvShowEntity>{
        return movieDao.getTvShow(tvShowId)
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
}