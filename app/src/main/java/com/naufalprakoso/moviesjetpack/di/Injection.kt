package com.naufalprakoso.moviesjetpack.di

import android.annotation.SuppressLint
import android.app.Application
import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import com.naufalprakoso.moviesjetpack.data.source.local.LocalRepository
import com.naufalprakoso.moviesjetpack.data.source.local.room.MovieDatabase
import com.naufalprakoso.moviesjetpack.data.source.remote.response.JsonHelper
import com.naufalprakoso.moviesjetpack.data.source.remote.RemoteRepository
import com.naufalprakoso.moviesjetpack.utils.AppExecutors

class Injection {
    companion object {
        @SuppressLint("VisibleForTests")
        fun provideRepository(application: Application): MovieRepository? {
            val database = MovieDatabase.getInstance(application)
            val localRepository = database?.movieDao()?.let { LocalRepository.getInstance(it) }

            val remoteRepository = RemoteRepository(
                JsonHelper(
                    application
                )
            )

            val appExecutors = AppExecutors()

            return MovieRepository.getInstance(localRepository, appExecutors)
        }
    }
}