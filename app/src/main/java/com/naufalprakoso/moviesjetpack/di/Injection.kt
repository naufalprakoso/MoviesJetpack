package com.naufalprakoso.moviesjetpack.di

import android.app.Application
import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import com.naufalprakoso.moviesjetpack.data.source.local.LocalRepository
import com.naufalprakoso.moviesjetpack.data.source.remote.JsonHelper
import com.naufalprakoso.moviesjetpack.data.source.remote.RemoteRepository

class Injection {
    companion object {
        fun provideRepository(application: Application): MovieRepository? {
            val localRepository = LocalRepository()
            val remoteRepository = RemoteRepository(JsonHelper(application))

            return MovieRepository.getInstance(localRepository, remoteRepository)
        }
    }
}