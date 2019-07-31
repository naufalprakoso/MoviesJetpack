package com.naufalprakoso.moviesjetpack.ui.detail

import androidx.test.rule.ActivityTestRule
import com.naufalprakoso.moviesjetpack.utils.FakeDataDummy
import org.junit.Rule
import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import com.naufalprakoso.moviesjetpack.utils.Const
import org.junit.After
import org.junit.Before
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.naufalprakoso.moviesjetpack.R
import com.naufalprakoso.moviesjetpack.utils.FetchingIdlingResource

class DetailMovieActivityTest {

    private val dummyMovie = FakeDataDummy.generateMovies()[0]
    private val fetchingIdlingResource = FetchingIdlingResource()

    @get:Rule
    var activityRule = object : ActivityTestRule<DetailMovieActivity>(DetailMovieActivity::class.java, false, false) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailMovieActivity::class.java)
            result.putExtra(Const.DETAIL_MOVIE, dummyMovie.id)
            result.putExtra(Const.MOVIE_TYPE, "movie")
            return result
        }
    }

    @Before
    fun setUp() {
        activityRule.launchActivity(null)
        IdlingRegistry.getInstance().register(fetchingIdlingResource)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovie.title)))

        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovie.overview)))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(withText(dummyMovie.genre)))

        onView(withId(R.id.tv_year_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year_duration)).check(matches(withText("(${dummyMovie.year}) - ${dummyMovie.duration}")))
    }
}