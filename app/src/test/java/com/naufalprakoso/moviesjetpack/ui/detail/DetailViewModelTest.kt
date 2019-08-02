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

class DetailViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

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
        val movieEntities = MutableLiveData<MovieEntity>().also {
            it.setValue(dummyMovie)
        }

        `when`(movieRepository.getMovie(movieId)).thenReturn(movieEntities)

        val observer = mock(Observer::class.java) as Observer<MovieEntity>
        viewModelMovie.getMovie()?.observeForever(observer)

        verify(movieRepository).getMovie(movieId)
    }

    @Test
    fun getTvShowDetail(){
        val tvShowEntities = MutableLiveData<TvShowEntity>().also {
            it.setValue(dummyTvShow)
        }

        `when`(movieRepository.getTvShow(tvShowId)).thenReturn(tvShowEntities)

        val observer = mock(Observer::class.java) as Observer<TvShowEntity>
        viewModelTvShow.getTvShow()?.observeForever(observer)

        verify(movieRepository).getTvShow(tvShowId)
    }
}