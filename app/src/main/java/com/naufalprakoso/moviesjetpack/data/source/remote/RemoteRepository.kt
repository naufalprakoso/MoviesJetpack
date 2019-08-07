package com.naufalprakoso.moviesjetpack.data.source.remote

import com.naufalprakoso.moviesjetpack.data.source.remote.response.MovieResponse
import com.naufalprakoso.moviesjetpack.data.source.remote.response.TvShowResponse
import android.os.Handler
import com.naufalprakoso.moviesjetpack.utils.EspressoIdlingResource

open class RemoteRepository(
    private val jsonHelper: JsonHelper
) {
    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
    }

    open fun getAllMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed(
            {
                callback.onAllMoviesReceived(jsonHelper.loadMovies())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    open fun getAllTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed(
            {
                callback.onAllTvShowsReceived(jsonHelper.loadTvShows())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    open fun getTvShow(tvShowId: String?, callback: GetTvShowCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed(
            {
                callback.onTvShowReceived(jsonHelper.getTvShow(tvShowId))
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    open fun getMovie(movieId: String?, callback: GetMovieCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed(
            {
                callback.onMovieReceived(jsonHelper.getMovie(movieId))
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponses: List<MovieResponse>)

        fun onDataNotAvailable()
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowResponses: List<TvShowResponse>)

        fun onDataNotAvailable()
    }

    interface GetMovieCallback {
        fun onMovieReceived(movieResponse: MovieResponse?)

        fun onDataNotAvailable()
    }

    interface GetTvShowCallback {
        fun onTvShowReceived(tvShowResponse: TvShowResponse?)

        fun onDataNotAvailable()
    }
}