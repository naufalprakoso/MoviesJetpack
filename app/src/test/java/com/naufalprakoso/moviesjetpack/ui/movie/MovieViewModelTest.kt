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
import androidx.paging.PagedList
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.vo.Resource

class MovieViewModelTest {

    private val username = "Naufal Prakoso"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel
    private val movieRepository = mock(MovieRepository::class.java)

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getMovies() {
        val pagedList = mock(PagedList::class.java) as PagedList<MovieEntity>

        val dummyMovie = MutableLiveData<Resource<PagedList<MovieEntity>>>().apply {
            setValue(Resource.success(pagedList))
        }

        `when`(movieRepository.getMoviesPaged()).thenReturn(dummyMovie)

        val observer = mock(Observer::class.java) as Observer<Resource<PagedList<MovieEntity>>>

        viewModel.setUsername(username)
        viewModel.getMoviesPaged()?.observeForever(observer)

        verify(observer).onChanged(Resource.success(pagedList))
    }
}