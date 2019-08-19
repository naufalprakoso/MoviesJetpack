package com.naufalprakoso.moviesjetpack.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.test.rule.ActivityTestRule
import com.naufalprakoso.moviesjetpack.testing.SingleFragmentActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.naufalprakoso.moviesjetpack.R
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.utils.*
import com.naufalprakoso.moviesjetpack.vo.Resource
import org.mockito.Mockito.*

class MovieFragmentTest {
    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val movieFragment = MovieFragment()

    private val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())

        val viewModel = mock(MovieViewModel::class.java) as MovieViewModel
        `when`(viewModel.getMoviesPaged()).thenReturn(movies)
        doNothing().`when`(viewModel).setUsername("Naufal Prakoso")

        movieFragment.factory = ViewModelUtil.createFor(viewModel)

        activityRule.activity.setFragment(movieFragment)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovies(){
        val expectedMovies = FakeDataDummy.generateMovies()
        movies.postValue(Resource.success(PagedListUtil.mockPagedList(expectedMovies)))

        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).check(RecyclerViewItemCountAssertion(expectedMovies.size))
    }
}