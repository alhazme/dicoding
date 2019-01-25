package me.alhaz.footballclubunittesting

import android.app.PendingIntent.getActivity
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.pressBack
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import me.alhaz.footballclubunittesting.R.id.*
import me.alhaz.footballclubunittesting.view.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.Espresso.onView
import org.hamcrest.CoreMatchers.*


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAddToFavoriteBehaviour() {

        onView(withId(navigation)).check(matches(isDisplayed()))
        onView(withId(navigation_prev)).perform(click())
        Thread.sleep(5000)
        onView(withId(list_layout)).check(matches(isDisplayed()))
        Espresso.onView(withText("Arsenal")).perform(click())
//        onView(withId(list_layout)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
//        onView(withId(list_layout)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(5000)
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText(endsWith("favorite"))).inRoot(withDecorView(not(`is`(activityRule.activity.getWindow().getDecorView())))).check(matches(isDisplayed()))
        Espresso.pressBack()

//        onView(withId(navigation)).check(matches(isDisplayed()))
//        onView(withId(navigation_favorites)).perform(click())
//        onView(withId(list_layout)).check(matches(isDisplayed()))
//        onView(withId(list_layout)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
//        onView(withId(list_layout)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
//        onView(withId(add_to_favorite)).perform(click())
//        onView(withText(endsWith("favorite"))).inRoot(withDecorView(not(`is`(activityRule.activity.getWindow().getDecorView())))).check(matches(isDisplayed()))
//        Espresso.pressBack()

        // Failed -_-
//        onData(anything()).inAdapterView(withId(list_layout)).atPosition(0).perform(click())
//        onData(withItemContent("Everton")).perform(click())

//        onData(withContentDescription("Everton")).onChildView(withId(tv_away)).perform(click())
//        onData(anything()).inAdapterView(withId(list_layout)).atPosition(0).onChildView(withId(tv_away)).check(matches(withText("Everton")))
//        onData(anything()).inAdapterView(withId(list_layout)).atPosition(0).onChildView(withId(tv_away)).check(matches(isDisplayed()))
//        onData(allOf(is(instanceOf<String>())))
//        onData(allOf(`is`(instanceOf(String::class.java)), "Espresso")).perform(click())
//        onView(withId(tv_away)).check(matches(withText("Everton")))
//        onView(withText("Leicester")).perform(click())
//        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
//        onView(withId(add_to_favorite)).perform(click())
//        onView(withText("Added to favorite")).check(matches(isDisplayed()))
//        pressBack()
//        onView(withId(navigation)).check(matches(isDisplayed()))
//        onView(withId(favorites)).perform(click())
//        onView(withText("Leicester")).check(matches(isDisplayed()))
    }

    @Test
    fun testRemoveToFavoriteBehaviour() {
        onView(withId(navigation)).check(matches(isDisplayed()))
        onView(withId(navigation_favorites)).perform(click())
        Thread.sleep(3000)
        onView(withId(list_layout)).check(matches(isDisplayed()))
        onView(withId(list_layout)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(list_layout)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
//        onView(withText(endsWith("favorite"))).check(matches(isDisplayed()))
        pressBack()
    }

//    fun withItemContent(expectedText: String): Matcher<Any> {
//        checkNotNull(expectedText)
//        return withItemContent(expectedText)
//    }
}