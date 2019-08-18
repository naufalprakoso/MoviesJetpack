package com.naufalprakoso.moviesjetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import androidx.lifecycle.Transformations
import com.naufalprakoso.moviesjetpack.vo.Resource


class MovieViewModel(
    private val movieRepository: MovieRepository? = null
) : ViewModel() {

    private val login = MutableLiveData<String>()

    fun getMovies(): LiveData<Resource<List<MovieEntity>>> =
        Transformations.switchMap(login) { movieRepository?.allMovies() }

    fun getFavoriteMovies(): LiveData<Resource<List<MovieEntity>>> =
        Transformations.switchMap(login) { movieRepository?.allFavoriteMovies() }

    fun setUsername(username: String) {
        login.value = username
    }
}