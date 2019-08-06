package me.alhaz.snippet.movieapp.views.movies.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.helper.EspressoIdlingResource
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import me.alhaz.snippet.movieapp.utils.RecyclerViewItemCountAssertion
import me.alhaz.snippet.movieapp.views.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieListFragmentTest {

    var dummyMovie: MovieEntity = DataDummy.generateListMovieEntity().get(0)

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
    fun `testListDetailFavoriteUnfavoriteMovie`() {

        // Show list movies

        onView(withId(R.id.rv_home_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_home_movies)).check(RecyclerViewItemCountAssertion(20))

        // Click first row to detail

        onView(withId(R.id.rv_home_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<MovieListViewHolder>(0, click()))

        // Show detail movie

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(ViewMatchers.withText(dummyMovie.title)))
        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(ViewMatchers.withText(dummyMovie.releaseDate.split("-").get(0))))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(ViewMatchers.withText(dummyMovie.overview)))

        // Set movie to favorite

        onView(withId(R.id.menu_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_favorite)).perform(click())

        // Back to movie list

        pressBack()

        // Change bottom navigation from home to favorite

        onView(withId(R.id.favorite_menu)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite_menu)).perform(click())

        // Show list favorite movie

        onView(withId(R.id.rv_favorite_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_movies)).check(RecyclerViewItemCountAssertion(1))

        // Click first row to detail

        onView(withId(R.id.rv_favorite_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<MovieListViewHolder>(0, click()))

        // Show detail favorite movie

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(ViewMatchers.withText(dummyMovie.title)))
        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(ViewMatchers.withText(dummyMovie.releaseDate.split("-").get(0))))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(ViewMatchers.withText(dummyMovie.overview)))

        // Set movie to unfavorite

        onView(withId(R.id.menu_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_favorite)).perform(click())

        // Back to favorite movie list

        pressBack()

        // Done
    }
}