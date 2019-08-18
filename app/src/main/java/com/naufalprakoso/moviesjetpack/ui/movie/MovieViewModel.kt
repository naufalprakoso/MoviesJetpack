package com.naufalprakoso.moviesjetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import androidx.lifecycle.Transformations
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteMovieEntity
import com.naufalprakoso.moviesjetpack.vo.Resource
import androidx.paging.PagedList




class MovieViewModel(
    private val movieRepository: MovieRepository? = null
) : ViewModel() {

    private val login = MutableLiveData<String>()

    @Deprecated("This method not implemented a pagination")
    fun getMovies(): LiveData<Resource<List<MovieEntity>>> =
        Transformations.switchMap(login) { movieRepository?.allMovies() }

    @Deprecated("This method not implemented a pagination")
    fun getFavoriteMovies(): LiveData<Resource<List<FavoriteMovieEntity>>> =
        Transformations.switchMap(login) { movieRepository?.allFavoriteMovies() }

    fun getMoviesPaged(): LiveData<Resource<PagedList<MovieEntity>>>? {
        return movieRepository?.getMoviesPaged()
    }

    fun getFavoriteMoviesPaged(): LiveData<Resource<PagedList<FavoriteMovieEntity>>>? {
        return movieRepository?.getFavoriteMoviesPaged()
    }

    fun setUsername(username: String) {
        login.value = username
    }
}