package com.naufalprakoso.moviesjetpack.data.source.remote

import com.naufalprakoso.moviesjetpack.data.source.remote.response.MovieResponse
import com.naufalprakoso.moviesjetpack.data.source.remote.response.TvShowResponse
import android.os.Handler
import androidx.lifecycle.LiveData
import com.naufalprakoso.moviesjetpack.utils.EspressoIdlingResource
import androidx.lifecycle.MutableLiveData
import com.naufalprakoso.moviesjetpack.data.source.remote.response.JsonHelper

open class RemoteRepository(
    private val jsonHelper: JsonHelper
) {
    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(jsonHelper: JsonHelper): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(jsonHelper)
            }

            return INSTANCE
        }
    }

    open fun getAllMoviesAsLiveData(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        val handler = Handler()
        handler.postDelayed(
            {
                resultMovie.value = ApiResponse.success(jsonHelper.loadMovies())
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            },
            SERVICE_LATENCY_IN_MILLIS
        )

        return resultMovie
    }

    open fun getAllTvShowsAsLiveData(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<TvShowResponse>>>()

        val handler = Handler()
        handler.postDelayed(
            {
                resultTvShow.value = ApiResponse.success(jsonHelper.loadTvShows())
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            },
            SERVICE_LATENCY_IN_MILLIS
        )

        return resultTvShow
    }

    open fun getTvShowAsLiveData(tvShowId: String?): LiveData<ApiResponse<TvShowResponse>> {
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<TvShowResponse>>()

        val handler = Handler()
        handler.postDelayed(
            {
                resultTvShow.value = jsonHelper.getTvShow(tvShowId)?.let { ApiResponse.success(it) }
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            },
            SERVICE_LATENCY_IN_MILLIS
        )

        return resultTvShow
    }

    open fun getMovieAsLiveData(movieId: String?): LiveData<ApiResponse<MovieResponse>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<MovieResponse>>()

        val handler = Handler()
        handler.postDelayed(
            {
                resultMovie.value = jsonHelper.getMovie(movieId)?.let { ApiResponse.success(it) }
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            },
            SERVICE_LATENCY_IN_MILLIS
        )

        return resultMovie
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