package com.naufalprakoso.moviesjetpack.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.naufalprakoso.moviesjetpack.data.source.local.LocalRepository
import com.naufalprakoso.moviesjetpack.data.source.remote.RemoteRepository
import com.naufalprakoso.moviesjetpack.ui.utils.FakeDataDummy
import com.naufalprakoso.moviesjetpack.ui.utils.InstantAppExecutors
import org.junit.Test

import com.naufalprakoso.moviesjetpack.ui.utils.LiveDataTestUtil
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.mockito.Mockito.*
import org.mockito.Mockito.`when`
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.ui.utils.PagedListUtil
import com.naufalprakoso.moviesjetpack.vo.Resource
import org.junit.Assert.assertNotNull

class MovieRepositoryTest {

    private fun <T> anyObject(): T {
        any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var local = mock(LocalRepository::class.java)
    private var remote = mock(RemoteRepository::class.java)
    private var instantAppExecutors = mock(InstantAppExecutors::class.java)
    private val movieRepository = FakeMovieRepository(local, remote, instantAppExecutors)

    private val movieEntities = FakeDataDummy.generateMovies()
    private val tvShowEntities = FakeDataDummy.generateTvShows()

    private val movieDetailResponse = FakeDataDummy.generateRemoteDummyMovies()[0]
    private val tvShowDetailResponse = FakeDataDummy.generateRemoteDummyTvShows()[0]

    private val movieDetailEntity = FakeDataDummy.generateMovies()[0]
    private val movieIdEntity = movieDetailResponse.id
    private val tvShowDetailEntity = FakeDataDummy.generateTvShows()[0]
    private val tvShowIdEntity = tvShowDetailResponse.id

    @Test
    fun allMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>

        `when`(local.getAllMoviesPaged()).thenReturn(dataSourceFactory)
        movieRepository.getMoviesPaged()
        val result = Resource.success(PagedListUtil.mockPagedList(movieEntities));

        verify(local).getAllMoviesPaged()
        assertNotNull(result.data)
        assertEquals(movieEntities.size, result.data?.size)
    }

    @Test
    fun allTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>

        `when`(local.getAllTvShowsPaged()).thenReturn(dataSourceFactory)
        movieRepository.getTvShowsPaged()
        val result = Resource.success(PagedListUtil.mockPagedList(tvShowEntities));

        verify(local).getAllTvShowsPaged()
        assertNotNull(result.data)
        assertEquals(tvShowEntities.size, result.data?.size)
    }

    @Test
    fun getMovie() {
        val dummyCourses = MutableLiveData<MovieEntity>()
        dummyCourses.value = movieDetailEntity

        `when`(movieIdEntity?.let { local.getMovie(it) }).thenReturn(dummyCourses)

        val result = LiveDataTestUtil.getValue(movieRepository.getMovie(movieIdEntity))

        movieIdEntity?.let { verify(local).getMovie(it) }
        assertNotNull(result.data)
        assertEquals(movieDetailEntity.title, result.data?.title)
    }

    @Test
    fun getTvShow() {
        val dummyCourses = MutableLiveData<TvShowEntity>()
        dummyCourses.value = tvShowDetailEntity

        `when`(tvShowIdEntity?.let { local.getTvShow(it) }).thenReturn(dummyCourses)

        val result = LiveDataTestUtil.getValue(movieRepository.getTvShow(tvShowIdEntity))

        tvShowIdEntity?.let { verify(local).getTvShow(it) }
        assertNotNull(result.data)
        assertEquals(tvShowDetailEntity.title, result.data?.title)
    }
}