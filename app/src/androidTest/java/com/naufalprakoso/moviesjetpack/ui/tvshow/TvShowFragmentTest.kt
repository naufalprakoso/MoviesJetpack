package com.naufalprakoso.moviesjetpack.ui.tvshow

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
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.utils.*
import com.naufalprakoso.moviesjetpack.vo.Resource
import org.mockito.Mockito.*

class TvShowFragmentTest {
    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val tvShowFragment = TvShowFragment()

    private val movies = MutableLiveData<Resource<PagedList<TvShowEntity>>>()

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())

        val viewModel = mock(TvShowViewModel::class.java) as TvShowViewModel
        `when`(viewModel.getTvShowsPaged()).thenReturn(movies)
        doNothing().`when`(viewModel).setUsername("Naufal Prakoso")

        tvShowFragment.factory = ViewModelUtil.createFor(viewModel)

        activityRule.activity.setFragment(tvShowFragment)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovies(){
        val expectedMovies = FakeDataDummy.generateTvShows()
        movies.postValue(Resource.success(PagedListUtil.mockPagedList(expectedMovies)))

        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).check(RecyclerViewItemCountAssertion(expectedMovies.size))
    }
}