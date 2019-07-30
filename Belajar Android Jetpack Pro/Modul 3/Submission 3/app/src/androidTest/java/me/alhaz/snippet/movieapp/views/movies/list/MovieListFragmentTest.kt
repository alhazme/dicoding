package me.alhaz.snippet.movieapp.views.movies.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.helper.EspressoIdlingResource
import me.alhaz.snippet.movieapp.utils.RecyclerViewItemCountAssertion
import me.alhaz.snippet.movieapp.views.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieListFragmentTest {

    var dummyMovie: Movie = DataDummy.generateListMovie().get(0)

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
    fun testLoadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).check(RecyclerViewItemCountAssertion(20))
    }

    @Test
    fun testToDetailMovie() {

        testLoadMovies()
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<MovieListViewHolder>(0, click()))

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(ViewMatchers.withText(dummyMovie.title)))
        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(ViewMatchers.withText(dummyMovie.releaseDate.split("-").get(0))))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(ViewMatchers.withText(dummyMovie.overview)))
    }
}