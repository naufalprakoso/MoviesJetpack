package com.naufalprakoso.moviesjetpack.data.source

import androidx.lifecycle.LiveData
import com.naufalprakoso.moviesjetpack.data.source.local.LocalRepository
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteMovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteTvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.remote.RemoteRepository
import com.naufalprakoso.moviesjetpack.data.source.remote.response.MovieResponse
import com.naufalprakoso.moviesjetpack.data.source.remote.response.TvShowResponse
import com.naufalprakoso.moviesjetpack.utils.AppExecutors
import com.naufalprakoso.moviesjetpack.vo.Resource
import com.naufalprakoso.moviesjetpack.data.source.remote.ApiResponse

class MovieRepository(
    private val localRepository: LocalRepository? = null,
    private val remoteRepository: RemoteRepository? = null,
    private val appExecutors: AppExecutors? = null
) : MovieDataSource {
    companion object {
        @Volatile
        private var INSTANCE: MovieRepository? = null

        fun getInstance(
            localRepository: LocalRepository?,
            remoteRepository: RemoteRepository?,
            appExecutors: AppExecutors?
        ): MovieRepository? {
            if (INSTANCE == null) {
                synchronized(MovieRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MovieRepository(localRepository, remoteRepository, appExecutors)
                    }
                }
            }

            return INSTANCE
        }
    }

    override fun allMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localRepository?.getAllMovies()!!
            }

            override fun shouldFetch(data: List<MovieEntity>): Boolean {
                return data.isEmpty()
            }

            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> {
                return remoteRepository?.getAllMoviesAsLiveData()!!
            }

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieEntities = ArrayList<MovieEntity>()

                for (movieResponse in data) {
                    println("LogMovieRepo: ${movieResponse.title}")
                    movieEntities.add(
                        MovieEntity(
                            movieResponse.id.toString(),
                            movieResponse.title,
                            movieResponse.overview,
                            movieResponse.rating,
                            movieResponse.genre,
                            movieResponse.image,
                            movieResponse.year,
                            movieResponse.duration
                        )
                    )
                }

                localRepository?.insertMovies(movieEntities)
            }
        }.asLiveData()
    }

    override fun allTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<TvShowEntity>> {
                return localRepository?.getAllTvShows()!!
            }

            override fun shouldFetch(data: List<TvShowEntity>): Boolean {
                return data.isEmpty()
            }

            public override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> {
                return remoteRepository?.getAllTvShowsAsLiveData()!!
            }

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowEntities = ArrayList<TvShowEntity>()

                for (tvShowResponse in data) {
                    tvShowEntities.add(
                        TvShowEntity(
                            tvShowResponse.id.toString(),
                            tvShowResponse.title,
                            tvShowResponse.overview,
                            tvShowResponse.rating,
                            tvShowResponse.genre,
                            tvShowResponse.image,
                            tvShowResponse.year,
                            tvShowResponse.episode
                        )
                    )
                }

                localRepository?.insertTvShows(tvShowEntities)
            }
        }.asLiveData()
    }

    override fun getMovie(movieId: String?): LiveData<Resource<MovieEntity>>? {
        return object : NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return movieId?.let { localRepository?.getMovie(it) }!!
            }

            override fun shouldFetch(data: MovieEntity): Boolean = false

            override fun createCall(): LiveData<ApiResponse<MovieResponse>>? = null

            override fun saveCallResult(data: MovieResponse) {}
        }.asLiveData()
    }

    override fun getTvShow(tvShowId: String?): LiveData<Resource<TvShowEntity>>? {
        return object : NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return tvShowId?.let { localRepository?.getTvShow(it) }!!
            }

            override fun shouldFetch(data: TvShowEntity): Boolean = false

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>>? = null

            override fun saveCallResult(data: TvShowResponse) {}
        }.asLiveData()
    }

    //    Favorite
    override fun allFavoriteMovies(): LiveData<Resource<List<FavoriteMovieEntity>>> {
        return object : NetworkBoundResource<List<FavoriteMovieEntity>, List<FavoriteMovieEntity>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<FavoriteMovieEntity>> {
                return localRepository?.getAllFavoriteMovies()!!
            }

            override fun shouldFetch(data: List<FavoriteMovieEntity>): Boolean = false

            public override fun createCall(): LiveData<ApiResponse<List<FavoriteMovieEntity>>>? = null

            override fun saveCallResult(data: List<FavoriteMovieEntity>) {}
        }.asLiveData()
    }

    override fun allFavoriteTvShows(): LiveData<Resource<List<FavoriteTvShowEntity>>> {
        return object : NetworkBoundResource<List<FavoriteTvShowEntity>, List<FavoriteTvShowEntity>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<FavoriteTvShowEntity>> {
                return localRepository?.getAllFavoriteTvShows()!!
            }

            override fun shouldFetch(data: List<FavoriteTvShowEntity>): Boolean = false

            public override fun createCall(): LiveData<ApiResponse<List<FavoriteTvShowEntity>>>? = null

            override fun saveCallResult(data: List<FavoriteTvShowEntity>) {}
        }.asLiveData()
    }

    override fun getFavoriteMovie(movieId: String?): LiveData<Resource<FavoriteMovieEntity>>? {
        return object : NetworkBoundResource<FavoriteMovieEntity, FavoriteMovieEntity>(appExecutors) {
            override fun loadFromDB(): LiveData<FavoriteMovieEntity> {
                return movieId?.let { localRepository?.getFavoriteMovie(it) }!!
            }

            override fun shouldFetch(data: FavoriteMovieEntity): Boolean = false

            override fun createCall(): LiveData<ApiResponse<FavoriteMovieEntity>>? = null

            override fun saveCallResult(data: FavoriteMovieEntity) {}
        }.asLiveData()
    }

    override fun getFavoriteTvShow(tvShowId: String?): LiveData<Resource<FavoriteTvShowEntity>>? {
        return object : NetworkBoundResource<FavoriteTvShowEntity, FavoriteTvShowEntity>(appExecutors) {
            override fun loadFromDB(): LiveData<FavoriteTvShowEntity> {
                return tvShowId?.let { localRepository?.getFavoriteTvShow(it) }!!
            }

            override fun shouldFetch(data: FavoriteTvShowEntity): Boolean = false

            override fun createCall(): LiveData<ApiResponse<FavoriteTvShowEntity>>? = null

            override fun saveCallResult(data: FavoriteTvShowEntity) {}
        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: FavoriteMovieEntity?) {
        appExecutors?.diskIO?.execute {
            movie?.let { localRepository?.insertFavoriteMovie(it) }
        }
    }

    override fun setFavoriteTvShow(tvShow: FavoriteTvShowEntity?) {
        appExecutors?.diskIO?.execute {
            tvShow?.let { localRepository?.insertFavoriteTvShow(it) }
        }
    }

    override fun unsetFavoriteMovie(movie: FavoriteMovieEntity?) {
        appExecutors?.diskIO?.execute {
            movie?.let { localRepository?.deleteFavoriteMovie(it) }
        }
    }

    override fun unsetFavoriteTvShow(tvShow: FavoriteTvShowEntity?) {
        appExecutors?.diskIO?.execute {
            tvShow?.let { localRepository?.deleteFavoriteTvShow(it) }
        }
    }
}