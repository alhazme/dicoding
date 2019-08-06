package me.alhaz.snippet.movieapp.views.tvshows.list

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
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
import java.lang.Thread.sleep

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
    fun `testListDetailFavoriteUnfavoriteTVShow`() {

        // Show list movies

        onView(withText("TV SHOWS")).check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).perform(click())

        // Show list movies

        onView(withId(R.id.rv_home_tvshows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_home_tvshows)).check(RecyclerViewItemCountAssertion(20))

        // Click first row to detail

        onView(withId(R.id.rv_home_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition<TVShowListViewHolder>(0, click()))

        // Show detail movie

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(ViewMatchers.withText(dummyTVShow.name)))
        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(ViewMatchers.withText(dummyTVShow.firstAirDate.split("-").get(0))))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(dummyTVShow.overview)))

        // Set movie to favorite

        onView(withId(R.id.menu_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_favorite)).perform(click())

        // Back to movie list

        Espresso.pressBack()

        // Change bottom navigation from home to favorite

        onView(withId(R.id.favorite_menu)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite_menu)).perform(click())

        // Change view pager to favorite tv show

        onView(withText("TV SHOWS")).check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).perform(click())

        // Show list favorite tvshow

        onView(withId(R.id.rv_favorite_tvshows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_tvshows)).check(RecyclerViewItemCountAssertion(1))

        // Click first row to detail

        onView(withId(R.id.rv_favorite_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition<TVShowListViewHolder>(0, click()))

        // Show detail favorite tv show

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(ViewMatchers.withText(dummyTVShow.name)))
        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(ViewMatchers.withText(dummyTVShow.firstAirDate.split("-").get(0))))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(dummyTVShow.overview)))

        // Set tv show to unfavorite

        onView(withId(R.id.menu_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_favorite)).perform(click())

        // Back to favorite tv show list

        Espresso.pressBack()

        // Done
    }
}