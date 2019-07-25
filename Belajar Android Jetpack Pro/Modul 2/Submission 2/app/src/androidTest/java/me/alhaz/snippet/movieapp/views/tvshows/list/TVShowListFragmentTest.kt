package me.alhaz.snippet.movieapp.views.tvshows.list

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.utils.RecyclerViewItemCountAssertion
import me.alhaz.snippet.movieapp.views.MainActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class TVShowListFragmentTest {

    var dummyTVShow: TVShow = DataDummy.generateTVShows().get(0)

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testLoadTVShows() {
        Espresso.onView(ViewMatchers.withText("TV SHOWS")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshows)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshows)).check(RecyclerViewItemCountAssertion(20))
    }

    @Test
    fun testToDetailTVShow() {

        testLoadTVShows()
        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition<TVShowListViewHolder>(0, ViewActions.click()));

        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(ViewMatchers.withId(R.id.tv_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tv_title)).check(ViewAssertions.matches(ViewMatchers.withText(dummyTVShow.name)))
        onView(ViewMatchers.withId(R.id.tv_year)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tv_year)).check(ViewAssertions.matches(ViewMatchers.withText(dummyTVShow.firstAirDate.split("-").get(0))))
        onView(ViewMatchers.withId(R.id.tv_description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tv_description)).check(ViewAssertions.matches(ViewMatchers.withText(dummyTVShow.overview)))
    }
}