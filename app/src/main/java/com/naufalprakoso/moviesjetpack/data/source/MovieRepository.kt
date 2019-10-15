package com.naufalprakoso.moviesjetpack.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.naufalprakoso.moviesjetpack.data.source.local.LocalRepository
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteMovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteTvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.remote.response.MovieResponse
import com.naufalprakoso.moviesjetpack.data.source.remote.response.TvShowResponse
import com.naufalprakoso.moviesjetpack.utils.AppExecutors
import com.naufalprakoso.moviesjetpack.vo.Resource
import com.naufalprakoso.moviesjetpack.data.source.remote.ApiResponse
import androidx.paging.LivePagedListBuilder
import com.naufalprakoso.moviesjetpack.api.MyRetrofitBuilder
import com.naufalprakoso.moviesjetpack.data.source.remote.response.MovieJSONResponse
import com.naufalprakoso.moviesjetpack.data.source.remote.response.TvShowJSONResponse

class MovieRepository(
    private val localRepository: LocalRepository? = null,
    private val appExecutors: AppExecutors? = null
) : MovieDataSource {

    companion object {
        @Volatile
        private var INSTANCE: MovieRepository? = null

        fun getInstance(
            localRepository: LocalRepository?,
            appExecutors: AppExecutors?
        ): MovieRepository? {
            if (INSTANCE == null) {
                synchronized(MovieRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MovieRepository(localRepository, appExecutors)
                    }
                }
            }

            return INSTANCE
        }
    }

    override fun getMovie(movieId: Int, api: String): LiveData<Resource<MovieEntity>>? {
        return object : NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                println("LogRepo: movieId: $movieId")
                return localRepository?.getMovie(movieId)!!
            }

            override fun shouldFetch(data: MovieEntity): Boolean = false
            override fun createCall(): LiveData<ApiResponse<MovieResponse>>? = null
            override fun saveCallResult(data: MovieResponse) {}
        }.asLiveData()
    }

    override fun getTvShow(tvShowId: Int, api: String): LiveData<Resource<TvShowEntity>>? {
        return object : NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return localRepository?.getTvShow(tvShowId)!!
            }

            override fun shouldFetch(data: TvShowEntity): Boolean = false
            override fun createCall(): LiveData<ApiResponse<TvShowResponse>>? = null
            override fun saveCallResult(data: TvShowResponse) {}
        }.asLiveData()
    }

    //    Favorite
    override fun getFavoriteMovie(movieId: Int): LiveData<Resource<FavoriteMovieEntity>>? {
        return object :
            NetworkBoundResource<FavoriteMovieEntity, FavoriteMovieEntity>(appExecutors) {
            override fun loadFromDB(): LiveData<FavoriteMovieEntity> {
                return localRepository?.getFavoriteMovie(movieId)!!
            }

            override fun shouldFetch(data: FavoriteMovieEntity): Boolean = false
            override fun createCall(): LiveData<ApiResponse<FavoriteMovieEntity>>? = null
            override fun saveCallResult(data: FavoriteMovieEntity) {}
        }.asLiveData()
    }

    override fun getFavoriteTvShow(tvShowId: Int): LiveData<Resource<FavoriteTvShowEntity>>? {
        return object :
            NetworkBoundResource<FavoriteTvShowEntity, FavoriteTvShowEntity>(appExecutors) {
            override fun loadFromDB(): LiveData<FavoriteTvShowEntity> {
                return localRepository?.getFavoriteTvShow(tvShowId)!!
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

    override fun checkFavoriteMovieState(movieId: Int): LiveData<Resource<List<FavoriteMovieEntity>>> {
        return object :
            NetworkBoundResource<List<FavoriteMovieEntity>, List<FavoriteMovieEntity>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<FavoriteMovieEntity>> {
                return localRepository?.checkFavoriteMovieState(movieId)!!
            }

            override fun shouldFetch(data: List<FavoriteMovieEntity>): Boolean = false
            public override fun createCall(): LiveData<ApiResponse<List<FavoriteMovieEntity>>>? =
                null

            override fun saveCallResult(data: List<FavoriteMovieEntity>) {}
        }.asLiveData()
    }

    override fun checkFavoriteTvShowState(tvShowId: Int): LiveData<Resource<List<FavoriteTvShowEntity>>> {
        return object :
            NetworkBoundResource<List<FavoriteTvShowEntity>, List<FavoriteTvShowEntity>>(
                appExecutors
            ) {
            public override fun loadFromDB(): LiveData<List<FavoriteTvShowEntity>> {
                return localRepository?.checkFavoriteTvShowState(tvShowId)!!
            }

            override fun shouldFetch(data: List<FavoriteTvShowEntity>): Boolean = false
            public override fun createCall(): LiveData<ApiResponse<List<FavoriteTvShowEntity>>>? =
                null

            override fun saveCallResult(data: List<FavoriteTvShowEntity>) {}
        }.asLiveData()
    }

    override fun getMoviesPaged(api: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, MovieJSONResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                return LivePagedListBuilder(localRepository?.getAllMoviesPaged()!!, 20).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>): Boolean = data.isEmpty()
            override fun createCall(): LiveData<ApiResponse<MovieJSONResponse>>? =
                MyRetrofitBuilder.apiService.getAllMovies(api)

            override fun saveCallResult(data: MovieJSONResponse) {
                val movieEntities = ArrayList<MovieEntity>()

                for (movieResponse in data.results) {
                    movieEntities.add(
                        MovieEntity(
                            movieResponse.id,
                            movieResponse.title,
                            movieResponse.overview,
                            movieResponse.vote_average,
                            movieResponse.poster_path,
                            movieResponse.release_date,
                            movieResponse.vote_count
                        )
                    )
                }

                localRepository?.insertMovies(movieEntities)
            }
        }.asLiveData()
    }

    override fun getTvShowsPaged(api: String): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, TvShowJSONResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                return LivePagedListBuilder(localRepository?.getAllTvShowsPaged()!!, 20).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>): Boolean = data.isEmpty()
            override fun createCall(): LiveData<ApiResponse<TvShowJSONResponse>>? =
                MyRetrofitBuilder.apiService.getAllTvShows(api)

            override fun saveCallResult(data: TvShowJSONResponse) {
                val tvShowEntities = ArrayList<TvShowEntity>()

                for (tvShowResponse in data.results) {
                    tvShowEntities.add(
                        TvShowEntity(
                            tvShowResponse.id,
                            tvShowResponse.name,
                            tvShowResponse.overview,
                            tvShowResponse.vote_average,
                            tvShowResponse.poster_path,
                            tvShowResponse.first_air_date,
                            tvShowResponse.vote_count
                        )
                    )
                }

                localRepository?.insertTvShows(tvShowEntities)
            }
        }.asLiveData()
    }

    override fun getFavoriteMoviesPaged(): LiveData<Resource<PagedList<FavoriteMovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<FavoriteMovieEntity>, List<FavoriteMovieEntity>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<FavoriteMovieEntity>> {
                return LivePagedListBuilder(
                    localRepository?.getAllFavoriteMoviesPaged()!!,
                    20
                ).build()
            }

            override fun shouldFetch(data: PagedList<FavoriteMovieEntity>): Boolean = false
            override fun createCall(): LiveData<ApiResponse<List<FavoriteMovieEntity>>>? = null
            override fun saveCallResult(data: List<FavoriteMovieEntity>) {}
        }.asLiveData()
    }

    override fun getFavoriteTvShowsPaged(): LiveData<Resource<PagedList<FavoriteTvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<FavoriteTvShowEntity>, List<FavoriteTvShowEntity>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<FavoriteTvShowEntity>> {
                return LivePagedListBuilder(
                    localRepository?.getAllFavoriteTvShowsPaged()!!,
                    20
                ).build()
            }

            override fun shouldFetch(data: PagedList<FavoriteTvShowEntity>): Boolean = false
            override fun createCall(): LiveData<ApiResponse<List<FavoriteTvShowEntity>>>? = null
            override fun saveCallResult(data: List<FavoriteTvShowEntity>) {}
        }.asLiveData()
    }
}