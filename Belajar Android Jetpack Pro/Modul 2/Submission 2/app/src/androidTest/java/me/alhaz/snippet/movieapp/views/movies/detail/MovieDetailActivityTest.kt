package me.alhaz.snippet.movieapp.views.movies.detail

import androidx.test.rule.ActivityTestRule
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import org.junit.Rule
import androidx.test.espresso.Espresso.onView
import android.content.Intent
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import me.alhaz.snippet.movieapp.R
import org.junit.Test


class MovieDetailActivityTest {

    var dummyMovie: Movie = DataDummy.generateListMovie().get(0)

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

    @Test
    fun loadMovieDetail() {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovie.title)))
        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(withText(dummyMovie.releaseDate.split("-").get(0))))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(dummyMovie.overview)))
    }
}