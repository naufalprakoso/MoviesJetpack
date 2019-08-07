package com.naufalprakoso.moviesjetpack.data.source.local

import androidx.lifecycle.LiveData
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
}