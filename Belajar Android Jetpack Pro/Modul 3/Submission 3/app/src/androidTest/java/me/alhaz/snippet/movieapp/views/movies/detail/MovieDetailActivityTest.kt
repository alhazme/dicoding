package me.alhaz.snippet.movieapp.views.movies.detail

import androidx.test.rule.ActivityTestRule
import me.alhaz.snippet.movieapp.data.DataDummy
import org.junit.Rule
import androidx.test.espresso.Espresso.onView
import android.content.Intent
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.helper.EspressoIdlingResource
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieDetailActivityTest {

    var dummyMovie: MovieEntity = DataDummy.generateListMovieEntity().get(0)

    @Rule
    @JvmField var activityRule: ActivityTestRule<MovieDetailActivity> =
        object : ActivityTestRule<MovieDetailActivity>(MovieDetailActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, MovieDetailActivity::class.java)
                result.putExtra("movie_id", dummyMovie.id)
                return result
            }
        }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @Test
    fun loadMovieDetail() {
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovie.title)))
        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(withText(dummyMovie.releaseDate.split("-").get(0))))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(dummyMovie.overview)))
    }
}