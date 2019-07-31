package com.naufalprakoso.moviesjetpack.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naufalprakoso.moviesjetpack.data.source.local.LocalRepository
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.remote.RemoteRepository
import com.naufalprakoso.moviesjetpack.data.source.remote.response.MovieResponse
import com.naufalprakoso.moviesjetpack.data.source.remote.response.TvShowResponse

class MovieRepository(
    private val localRepository: LocalRepository? = null,
    private val remoteRepository: RemoteRepository? = null
) : MovieDataSource {
    companion object {
        @Volatile
        private var INSTANCE: MovieRepository? = null

        fun getInstance(localRepository: LocalRepository?, remoteRepository: RemoteRepository?): MovieRepository? {
            if (INSTANCE == null) {
                synchronized(MovieRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MovieRepository(localRepository, remoteRepository)
                    }
                }
            }

            return INSTANCE
        }
    }

    override fun allMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()

        remoteRepository?.getAllMovies(object : RemoteRepository.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()

                for (i in movieResponses.indices) {
                    val response = movieResponses[i]
                    val movie = MovieEntity(
                        id = response.id,
                        title = response.title,
                        overview = response.overview,
                        image = response.image
                    )

                    movieList.add(movie)
                }

                movieResults.postValue(movieList)
            }

            override fun onDataNotAvailable() {

            }
        })
        return movieResults
    }

    override fun allTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()

        remoteRepository?.getAllTvShows(object : RemoteRepository.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponses: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()

                for (i in tvShowResponses.indices) {
                    val response = tvShowResponses[i]
                    val tvShow = TvShowEntity(
                        id = response.id,
                        title = response.title,
                        overview = response.overview,
                        image = response.image
                    )

                    tvShowList.add(tvShow)
                }

                tvShowResults.postValue(tvShowList)
            }

            override fun onDataNotAvailable() {

            }
        })

        return tvShowResults
    }

    override fun getMovie(movieId: String?): LiveData<MovieEntity>? {
        val movie = MutableLiveData<MovieEntity>()

        remoteRepository?.getAllMovies(object : RemoteRepository.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<MovieResponse>) {
                for (i in movieResponses.indices) {
                    val movieResponse = movieResponses[i]

                    val id = movieResponse.id

                    if (id == movieId) {
                        movie.postValue(
                            MovieEntity(
                                id,
                                movieResponse.title,
                                movieResponse.overview,
                                movieResponse.rating,
                                movieResponse.genre,
                                movieResponse.image,
                                movieResponse.year,
                                movieResponse.duration
                            )
                        )

                        break
                    }
                }
            }

            override fun onDataNotAvailable() {
            }
        })

        return movie
    }

    override fun getTvShow(tvShowId: String?): LiveData<TvShowEntity>? {
        val tvShow = MutableLiveData<TvShowEntity>()

        remoteRepository?.getAllTvShows(object : RemoteRepository.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponses: List<TvShowResponse>) {
                for (i in tvShowResponses.indices) {
                    val tvShowResponse = tvShowResponses[i]

                    val id = tvShowResponse.id

                    if (id == tvShowId) {
                        tvShow.postValue(
                            TvShowEntity(
                                id,
                                tvShowResponse.title,
                                tvShowResponse.overview,
                                tvShowResponse.rating,
                                tvShowResponse.genre,
                                tvShowResponse.image,
                                tvShowResponse.year,
                                tvShowResponse.episode
                            )
                        )

                        break
                    }
                }
            }

            override fun onDataNotAvailable() {
            }
        })

        return tvShow
    }
}