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
import androidx.paging.PagedList
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.vo.Resource
import org.junit.Rule

class TvShowViewModelTest {

    private val username = "Naufal Prakoso"

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
        val pagedList = mock(PagedList::class.java) as PagedList<TvShowEntity>

        val dummyMovie = MutableLiveData<Resource<PagedList<TvShowEntity>>>().apply {
            setValue(Resource.success(pagedList))
        }

        Mockito.`when`(movieRepository.getTvShowsPaged()).thenReturn(dummyMovie)

        val observer =
            mock(Observer::class.java) as Observer<Resource<PagedList<TvShowEntity>>>

        viewModel.setUsername(username)
        viewModel.getTvShowsPaged()?.observeForever(observer)

        Mockito.verify(observer).onChanged(Resource.success(pagedList))
    }
}