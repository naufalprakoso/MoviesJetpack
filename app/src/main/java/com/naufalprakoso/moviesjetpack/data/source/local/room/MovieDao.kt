package com.naufalprakoso.moviesjetpack.data.source.local.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.*
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity

@Dao
interface MovieDao{
    @WorkerThread
    @Query("SELECT * FROM movies")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovie(movieId: String): LiveData<MovieEntity>

    @WorkerThread
    @Query("SELECT * FROM tvshows")
    fun getTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tvshows WHERE id = :tvShowId")
    fun getTvShow(tvShowId: String): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<MovieEntity>): Long

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateMovie(movie: MovieEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShows: List<TvShowEntity>): Long

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateTvShow(tvShow: TvShowEntity): Int
}