package com.naufalprakoso.moviesjetpack.data.source.remote

import com.naufalprakoso.moviesjetpack.data.source.remote.response.MovieResponse
import com.naufalprakoso.moviesjetpack.data.source.remote.response.TvShowResponse
import android.os.Handler

class RemoteRepository(
    private val jsonHelper: JsonHelper
) {
    companion object {
        private var INSTANCE: RemoteRepository? = null
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        fun getInstance(helper: JsonHelper): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(helper)
            }

            return INSTANCE
        }
    }

    fun getAllMovies(): List<MovieResponse> {
        return jsonHelper.loadMovies()
    }

    fun getAllTvShows(): List<TvShowResponse> {
        return jsonHelper.loadTvShows()
    }

    fun getAllMovies(callback: LoadMoviesCallback) {
        val handler = Handler()
        handler.postDelayed(
            { callback.onAllMoviesReceived(jsonHelper.loadMovies()) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getAllTvShows(callback: LoadTvShowsCallback) {
        val handler = Handler()
        handler.postDelayed(
            { callback.onAllTvShowsReceived(jsonHelper.loadTvShows()) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

//    fun getTvShows(courseId: String, callback: LoadTvShowsCallback) {
//        val handler = Handler()
//        handler.postDelayed(
//            { callback.onAllTvShowsReceived(jsonHelper.loadTvShows()) },
//            SERVICE_LATENCY_IN_MILLIS
//        )
//    }

//    fun getContent(moduleId: String, callback: GetMovieCallback) {
//        val handler = Handler()
//        handler.postDelayed({ callback.onMovieReceived(jsonHelper.loadMovies(moduleId)) }, SERVICE_LATENCY_IN_MILLIS)
//    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponses: List<MovieResponse>)

        fun onDataNotAvailable()
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowResponses: List<TvShowResponse>)

        fun onDataNotAvailable()
    }

    interface GetMovieCallback {
        fun onMovieReceived(movieResponse: MovieResponse)

        fun onDataNotAvailable()
    }

    interface GetTvShowCallback {
        fun onTvShowReceived(tvShowResponse: TvShowResponse)

        fun onDataNotAvailable()
    }
}