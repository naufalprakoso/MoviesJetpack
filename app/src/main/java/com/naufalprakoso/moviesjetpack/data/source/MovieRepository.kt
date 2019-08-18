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
                            id = movieResponse.id.toString(),
                            title = movieResponse.title,
                            overview = movieResponse.overview,
                            image = movieResponse.image
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
                            id = tvShowResponse.id.toString(),
                            title = tvShowResponse.title,
                            overview = tvShowResponse.overview,
                            image = tvShowResponse.image
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

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> {
                return remoteRepository?.getMovieAsLiveData(movieId)!!
            }

            override fun saveCallResult(data: MovieResponse) {
                movieId?.let { localRepository?.getMovie(it) }
            }
        }.asLiveData()
    }

    override fun getTvShow(tvShowId: String?): LiveData<Resource<TvShowEntity>>? {
        return object : NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return tvShowId?.let { localRepository?.getTvShow(it) }!!
            }

            override fun shouldFetch(data: TvShowEntity): Boolean = false

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>> {
                return remoteRepository?.getTvShowAsLiveData(tvShowId)!!
            }

            override fun saveCallResult(data: TvShowResponse) {
                tvShowId?.let { localRepository?.getTvShow(it) }
            }
        }.asLiveData()
    }

    //    Favorite
    override fun allFavoriteMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localRepository?.getAllFavoriteMovies()!!
            }

            override fun shouldFetch(data: List<MovieEntity>): Boolean = false

            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>>? = null

            override fun saveCallResult(data: List<MovieResponse>) {}
        }.asLiveData()
    }

    override fun allFavoriteTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<TvShowEntity>> {
                return localRepository?.getAllFavoriteTvShows()!!
            }

            override fun shouldFetch(data: List<TvShowEntity>): Boolean = false

            public override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>>? = null

            override fun saveCallResult(data: List<TvShowResponse>) {}
        }.asLiveData()
    }

    override fun getFavoriteMovie(movieId: String?): LiveData<Resource<MovieEntity>>? {
        return object : NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return movieId?.let { localRepository?.getFavoriteMovie(it) }!!
            }

            override fun shouldFetch(data: MovieEntity): Boolean = false

            override fun createCall(): LiveData<ApiResponse<MovieResponse>>? = null

            override fun saveCallResult(data: MovieResponse) {}
        }.asLiveData()
    }

    override fun getFavoriteTvShow(tvShowId: String?): LiveData<Resource<TvShowEntity>>? {
        return object : NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return tvShowId?.let { localRepository?.getFavoriteTvShow(it) }!!
            }

            override fun shouldFetch(data: TvShowEntity): Boolean = false

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>>? = null

            override fun saveCallResult(data: TvShowResponse) {}
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