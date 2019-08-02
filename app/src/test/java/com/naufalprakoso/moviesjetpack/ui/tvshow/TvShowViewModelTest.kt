package com.naufalprakoso.moviesjetpack.ui.tvshow

import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.mockito.Mockito
import org.mockito.Mockito.mock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import org.junit.Rule

class TvShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TvShowViewModel
    private val movieRepository = mock(MovieRepository::class.java)

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieRepository)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getTvShows() {
        val dummyCourse = MutableLiveData<List<TvShowEntity>>().apply {
            setValue(com.naufalprakoso.moviesjetpack.ui.utils.FakeDataDummy.generateTvShows())
        }

        Mockito.`when`(movieRepository.allTvShows()).thenReturn(dummyCourse)

        val observer = mock(Observer::class.java) as Observer<List<TvShowEntity>>

        viewModel.getTvShows()?.observeForever(observer)

        Mockito.verify(movieRepository).allTvShows()
    }
}