package com.naufalprakoso.moviesjetpack.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import com.naufalprakoso.moviesjetpack.di.Injection
import androidx.lifecycle.ViewModel
import com.naufalprakoso.moviesjetpack.ui.detail.DetailViewModel
import com.naufalprakoso.moviesjetpack.ui.movie.MovieViewModel
import com.naufalprakoso.moviesjetpack.ui.tvshow.TvShowViewModel

class ViewModelFactory(
    private var movieRepository: MovieRepository? = null
) : ViewModelProvider.NewInstanceFactory() {

    private var movieId: String? = ""

    constructor(movieRepository: MovieRepository? = null, id: String? = "") : this(movieRepository) {
        movieId = id
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(Injection.provideRepository(application))
                    }
                }
            }

            return INSTANCE
        }
    }

    fun getInstanceDetail(application: Application): ViewModelFactory? {
        if (INSTANCE == null) {
            synchronized(ViewModelFactory::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(application), movieId)
                }
            }
        }

        return INSTANCE
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(movieRepository) as T
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> TvShowViewModel(movieRepository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(movieId, movieRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }

    }
}