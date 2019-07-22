package me.alhaz.snippet.movieapp.views.tvshows.list

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.utils.RecyclerViewItemCountAssertion
import me.alhaz.snippet.movieapp.views.MainActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class TVShowListFragmentTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testLoadTVShows() {
        Espresso.onView(ViewMatchers.withText("TV SHOWS")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshows)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshows)).check(RecyclerViewItemCountAssertion(20))
    }
}