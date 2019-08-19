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
}