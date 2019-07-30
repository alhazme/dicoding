package me.alhaz.snippet.movieapp.views.tvshows.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.helper.EspressoIdlingResource
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.utils.RecyclerViewItemCountAssertion
import me.alhaz.snippet.movieapp.views.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TVShowListFragmentTest {

    var dummyTVShow: TVShow = DataDummy.generateTVShows().get(0)

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @Test
    fun testLoadTVShows() {
        onView(withText("TV SHOWS")).check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tvshows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshows)).check(RecyclerViewItemCountAssertion(20))
    }

    @Test
    fun testToDetailTVShow() {

        testLoadTVShows()
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition<TVShowListViewHolder>(0, click()))

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(ViewMatchers.withText(dummyTVShow.name)))
        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(ViewMatchers.withText(dummyTVShow.firstAirDate.split("-").get(0))))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(dummyTVShow.overview)))
    }
}