package com.naufalprakoso.moviesjetpack.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.naufalprakoso.moviesjetpack.data.source.local.LocalRepository
import com.naufalprakoso.moviesjetpack.data.source.remote.RemoteRepository
import com.naufalprakoso.moviesjetpack.ui.utils.FakeDataDummy
import org.junit.Test

import com.naufalprakoso.moviesjetpack.ui.utils.LiveDataTestUtil
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.mockito.Mockito.*

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
    private val movieRepository = FakeMovieRepository(remote)

    private val movieResponses = FakeDataDummy.generateRemoteDummyMovies()
    private val tvShowResponses = FakeDataDummy.generateRemoteDummyTvShows()
    private val movieDetailResponse = FakeDataDummy.generateRemoteDummyMovies()[0]
    private val movieId = movieDetailResponse.id
    private val tvShowDetailResponse = FakeDataDummy.generateRemoteDummyTvShows()[0]
    private val tvShowId = tvShowDetailResponse.id

    @Test
    fun allMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteRepository.LoadMoviesCallback).onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(anyObject())

        val result = LiveDataTestUtil.getValue(movieRepository.allMovies())

        verify(remote, times(1)).getAllMovies(anyObject())

        assertEquals(movieResponses.size, result.size)
    }

    @Test
    fun allTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteRepository.LoadTvShowsCallback)
                .onAllTvShowsReceived(tvShowResponses)
            null
        }.`when`(remote).getAllTvShows(anyObject())

        val result = LiveDataTestUtil.getValue(movieRepository.allTvShows())

        verify(remote, times(1)).getAllTvShows(anyObject())

        assertEquals(tvShowResponses.size, result.size)
    }

    @Test
    fun getMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteRepository.GetMovieCallback)
                .onMovieReceived(movieDetailResponse)
            null
        }.`when`(remote).getMovie(eq(movieId), anyObject())

        val resultMovie = LiveDataTestUtil.getValue(movieRepository.getMovie(movieId))

        verify(remote, times(1))
            .getMovie(eq(movieId), anyObject())

        assertEquals(movieDetailResponse.title, resultMovie.title)
    }

    @Test
    fun getTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteRepository.GetMovieCallback)
                .onMovieReceived(movieDetailResponse)
            null
        }.`when`(remote).getMovie(eq(tvShowId), anyObject())

        val resultMovie = LiveDataTestUtil.getValue(movieRepository.getMovie(tvShowId))

        verify(remote, times(1))
            .getMovie(eq(tvShowId), anyObject())

        assertEquals(movieDetailResponse.title, resultMovie.title)
    }
}