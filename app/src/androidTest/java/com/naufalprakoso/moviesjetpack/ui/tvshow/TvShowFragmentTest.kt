package com.naufalprakoso.moviesjetpack.ui.tvshow

import androidx.test.rule.ActivityTestRule
import com.naufalprakoso.moviesjetpack.testing.SingleFragmentActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.naufalprakoso.moviesjetpack.R
import com.naufalprakoso.moviesjetpack.utils.RecyclerViewItemCountAssertion

class TvShowFragmentTest {
    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val tvShowFragment = TvShowFragment()

    @Before
    fun setUp(){
        activityRule.activity.setFragment(tvShowFragment)
    }

    @After
    fun tearDown(){

    }

    @Test
    fun loadMovies(){
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).check(RecyclerViewItemCountAssertion(10))
    }
}