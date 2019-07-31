package com.naufalprakoso.moviesjetpack.ui.detail

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp(){
        viewModel = DetailViewModel()
    }

    @After
    fun tearDown(){

    }

    @Test
    fun getMovieDetail(){
        viewModel.movieId = "429617"
        val movie = viewModel.getMovie()
        assertNotNull(movie)
        assertEquals("Spider-Man: Far from Home", movie?.title)
    }

    @Test
    fun getTvShowDetail(){
        viewModel.movieId = "38422"
        val tvShow = viewModel.getTvShow()
        assertNotNull(tvShow)
        assertEquals("Jessica Jones Season 2", tvShow?.title)
    }
}