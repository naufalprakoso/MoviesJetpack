package com.naufalprakoso.moviesjetpack.ui.detail

import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import com.naufalprakoso.moviesjetpack.ui.utils.FakeDataDummy
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.mockito.Mockito.`when`
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.vo.Resource

class DetailViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val username = "Username"

    private lateinit var viewModelMovie: DetailViewModel
    private lateinit var viewModelTvShow: DetailViewModel
    private val movieRepository = mock(MovieRepository::class.java)
    private val dummyMovie = FakeDataDummy.generateMovies()[0]
    private val dummyTvShow = FakeDataDummy.generateTvShows()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp(){
        viewModelMovie = DetailViewModel(movieId, movieRepository)
        viewModelTvShow = DetailViewModel(tvShowId, movieRepository)
    }

    @After
    fun tearDown(){

    }

    @Test
    fun getMovieDetail(){
        val resource: Resource<MovieEntity> = Resource.success(dummyMovie)

        val movieEntities = MutableLiveData<Resource<MovieEntity>>().also {
            it.setValue(resource)
        }

        `when`(movieRepository.getMovie(movieId)).thenReturn(movieEntities)

        val observer = mock(Observer::class.java) as Observer<Resource<MovieEntity>>

        viewModelMovie.setUsername(username)
        viewModelMovie.getMovie().observeForever(observer)

        verify(observer).onChanged(resource)
    }

    @Test
    fun getTvShowDetail(){
        val resource: Resource<TvShowEntity> = Resource.success(dummyTvShow)

        val movieEntities = MutableLiveData<Resource<TvShowEntity>>().also {
            it.setValue(resource)
        }

        `when`(movieRepository.getTvShow(tvShowId)).thenReturn(movieEntities)

        val observer = mock(Observer::class.java) as Observer<Resource<TvShowEntity>>

        viewModelTvShow.setUsername(username)
        viewModelTvShow.getTvShow().observeForever(observer)

        verify(observer).onChanged(resource)
    }
}