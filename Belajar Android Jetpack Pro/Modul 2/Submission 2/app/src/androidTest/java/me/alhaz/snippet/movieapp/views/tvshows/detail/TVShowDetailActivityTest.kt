package me.alhaz.snippet.movieapp.views.tvshows.detail

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.helper.EspressoIdlingResource
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TVShowDetailActivityTest {

    var dummyTVShow: TVShow = DataDummy.generateTVShows().get(0)

    @Rule
    @JvmField var activityRule: ActivityTestRule<TVShowDetailActivity> =
        object : ActivityTestRule<TVShowDetailActivity>(TVShowDetailActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, TVShowDetailActivity::class.java)
                result.putExtra("tvshow_id", dummyTVShow.id)
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
    fun loadTVShowDetail() {
        Espresso.onView(ViewMatchers.withId(R.id.tv_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_title)).check(ViewAssertions.matches(ViewMatchers.withText(dummyTVShow.name)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_year)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_year)).check(ViewAssertions.matches(ViewMatchers.withText(dummyTVShow.firstAirDate.split("-").get(0))))
        Espresso.onView(ViewMatchers.withId(R.id.tv_rating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_rating)).check(ViewAssertions.matches(ViewMatchers.withText(dummyTVShow.voteAverage.toString())))
        Espresso.onView(ViewMatchers.withId(R.id.tv_runtime)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_runtime)).check(ViewAssertions.matches(ViewMatchers.withText("${dummyTVShow.numberOfEpisodes} Episodes")))
    }

}