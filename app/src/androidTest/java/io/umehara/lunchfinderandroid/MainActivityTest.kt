package io.umehara.lunchfinderandroid

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import org.hamcrest.Matchers.hasToString
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun detailContainsDefaultValues() {
        onView(withId(R.id.restaurant_name)).check(matches(withText("Restaurant Name")))
    }

    @Test
    fun detailContainsSelectedValues() {
        onData(hasToString("First")).perform(click())


        onView(withId(R.id.restaurant_name)).check(matches(withText("First")))
    }
}