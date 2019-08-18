package com.naufalprakoso.moviesjetpack.data.source.local.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.*
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteMovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteTvShowEntity
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
    fun insertMovie(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShows: List<TvShowEntity>)

    //    Favorite
    @WorkerThread
    @Query("SELECT * FROM fav_movies")
    fun getFavoriteMovies(): LiveData<List<FavoriteMovieEntity>>

    @Query("SELECT * FROM fav_movies WHERE id = :movieId")
    fun getFavoriteMovie(movieId: String): LiveData<FavoriteMovieEntity>

    @WorkerThread
    @Query("SELECT * FROM fav_tvshows")
    fun getFavoriteTvShows(): LiveData<List<FavoriteTvShowEntity>>

    @Query("SELECT * FROM fav_tvshows WHERE id = :tvShowId")
    fun getFavoriteTvShow(tvShowId: String): LiveData<FavoriteTvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(movie: FavoriteMovieEntity)

    @Delete
    fun deleteFavoriteMovie(movie: FavoriteMovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteTvShow(tvShow: FavoriteTvShowEntity)

    @Delete
    fun deleteFavoriteTvShow(tvShow: FavoriteTvShowEntity)

    @Query("SELECT id FROM fav_movies WHERE id = :movieId")
    fun checkFavoriteMovieState(movieId: String): LiveData<List<FavoriteMovieEntity>>

    @Query("SELECT id FROM fav_tvshows WHERE id = :tvShowId")
    fun checkFavoriteTvShowState(tvShowId: String): LiveData<List<FavoriteTvShowEntity>>
}