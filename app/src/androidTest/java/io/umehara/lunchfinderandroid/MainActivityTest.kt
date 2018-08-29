package io.umehara.lunchfinderandroid

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import org.junit.Rule
import org.junit.Test


class MainActivityTest {
    @get:Rule
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun detailContainsFirstRestaurantValue() {
        onView(withId(R.id.restaurant_name)).check(matches(withText("Afuri")))
        onView(withId(R.id.restaurant_name_jp)).check(matches(withText("あふり")))
    }

    @Test
    fun detailContainsSelectedRestaurant() {
        onView(withId(R.id.restaurants_recycler_view))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))


        onView(withId(R.id.restaurant_name)).check(matches(withText("Butagumi")))
        onView(withId(R.id.restaurant_name_jp)).check(matches(withText("豚組")))
    }
}