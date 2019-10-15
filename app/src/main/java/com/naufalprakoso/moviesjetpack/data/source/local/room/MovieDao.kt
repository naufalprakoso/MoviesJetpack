package com.naufalprakoso.moviesjetpack.data.source.local.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteMovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteTvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity

@Dao
interface MovieDao{
    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovie(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tvshows WHERE id = :tvShowId")
    fun getTvShow(tvShowId: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShows: List<TvShowEntity>)

    //    Favorite
    @Query("SELECT * FROM fav_movies WHERE id = :movieId")
    fun getFavoriteMovie(movieId: Int): LiveData<FavoriteMovieEntity>

    @Query("SELECT * FROM fav_tvshows WHERE id = :tvShowId")
    fun getFavoriteTvShow(tvShowId: Int): LiveData<FavoriteTvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(movie: FavoriteMovieEntity)

    @Delete
    fun deleteFavoriteMovie(movie: FavoriteMovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteTvShow(tvShow: FavoriteTvShowEntity)

    @Delete
    fun deleteFavoriteTvShow(tvShow: FavoriteTvShowEntity)

    @WorkerThread
    @Query("SELECT id FROM fav_movies WHERE id = :movieId")
    fun checkFavoriteMovieState(movieId: Int): LiveData<List<FavoriteMovieEntity>>

    @WorkerThread
    @Query("SELECT id FROM fav_tvshows WHERE id = :tvShowId")
    fun checkFavoriteTvShowState(tvShowId: Int): LiveData<List<FavoriteTvShowEntity>>

    // Paging
    @WorkerThread
    @Query("SELECT * FROM movies")
    fun getMovieAsPaged(): DataSource.Factory<Int, MovieEntity>

    @WorkerThread
    @Query("SELECT * FROM tvshows")
    fun getTvShowAsPaged(): DataSource.Factory<Int, TvShowEntity>

    @WorkerThread
    @Query("SELECT * FROM fav_movies")
    fun getFavoriteMovieAsPaged(): DataSource.Factory<Int, FavoriteMovieEntity>

    @WorkerThread
    @Query("SELECT * FROM fav_tvshows")
    fun getFavoriteTvShowAsPaged(): DataSource.Factory<Int, FavoriteTvShowEntity>
}