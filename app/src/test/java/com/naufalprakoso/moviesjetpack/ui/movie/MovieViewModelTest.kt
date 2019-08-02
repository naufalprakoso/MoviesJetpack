package com.naufalprakoso.moviesjetpack.ui.movie

import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import com.naufalprakoso.moviesjetpack.ui.utils.FakeDataDummy
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.mockito.Mockito.*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.mockito.Mockito.`when`
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity

class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel
    private val movieRepository = mock(MovieRepository::class.java)

    @Before
    fun setUp(){
        viewModel = MovieViewModel(movieRepository)
    }

    @After
    fun tearDown(){

    }

    @Test
    fun getMovies() {
        val dummyCourse = MutableLiveData<List<MovieEntity>>().apply {
            setValue(FakeDataDummy.generateMovies())
        }

        `when`(movieRepository.allMovies()).thenReturn(dummyCourse)

        val observer = mock(Observer::class.java) as Observer<List<MovieEntity>>

        viewModel.getMovies()?.observeForever(observer)

        verify(movieRepository).allMovies()
    }
}