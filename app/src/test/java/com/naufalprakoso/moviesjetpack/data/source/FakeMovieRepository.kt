package com.naufalprakoso.moviesjetpack.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.remote.RemoteRepository
import com.naufalprakoso.moviesjetpack.data.source.remote.response.MovieResponse
import com.naufalprakoso.moviesjetpack.data.source.remote.response.TvShowResponse

class FakeMovieRepository(
    private val remoteRepository: RemoteRepository? = null
) : MovieDataSource {

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

        remoteRepository?.getMovie(movieId, object : RemoteRepository.GetMovieCallback {
            override fun onMovieReceived(movieResponse: MovieResponse?) {
                movie.postValue(
                    MovieEntity(
                        movieId,
                        movieResponse?.title,
                        movieResponse?.overview,
                        movieResponse?.rating,
                        movieResponse?.genre,
                        movieResponse?.image,
                        movieResponse?.year,
                        movieResponse?.duration
                    )
                )
            }

            override fun onDataNotAvailable() {

            }
        })

        return movie
    }

    override fun getTvShow(tvShowId: String?): LiveData<TvShowEntity>? {
        val tvShow = MutableLiveData<TvShowEntity>()

        remoteRepository?.getTvShow(tvShowId, object : RemoteRepository.GetTvShowCallback{
            override fun onTvShowReceived(tvShowResponse: TvShowResponse?) {
                tvShow.postValue(
                    TvShowEntity(
                        tvShowId,
                        tvShowResponse?.title,
                        tvShowResponse?.overview,
                        tvShowResponse?.rating,
                        tvShowResponse?.genre,
                        tvShowResponse?.image,
                        tvShowResponse?.year,
                        tvShowResponse?.episode
                    )
                )
            }

            override fun onDataNotAvailable() {

            }
        })

        return tvShow
    }

}