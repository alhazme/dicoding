package me.alhaz.snippet.movieapp.views.tvshows.detail

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import org.junit.Rule
import org.junit.Test

class TVShowDetailActivityTest {

    var dummyTVShow: TVShow = DataDummy.listTVShows().get(2)

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

    @Test
    fun loadTVShowDetail() {
        Espresso.onView(ViewMatchers.withId(R.id.tv_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTVShow.title)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_year)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_year))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTVShow.year.toString())))
        Espresso.onView(ViewMatchers.withId(R.id.tv_rating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_rating))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTVShow.score.toString())))
        Espresso.onView(ViewMatchers.withId(R.id.tv_runtime)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_runtime))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTVShow.runtime)))
    }

}