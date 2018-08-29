package io.umehara.lunchfinderandroid

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.google.android.gms.maps.MapView
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun detailContainsFirstRestaurantValue() {
        onView(withId(R.id.restaurant_name)).check(matches(withText("Afuri")))
        onView(withId(R.id.restaurant_name_jp)).check(matches(withText("あふり")))

        val restaurantCategoriesRecyclerView = rule.activity.findViewById<RecyclerView>(R.id.restaurant_categories_recycler_view)
        val recyclerViewSize = restaurantCategoriesRecyclerView.childCount
        assertThat(recyclerViewSize, equalTo(1))
    }

    @Test
    fun restaurantRecyclerViewIsCorrectSize() {
        val restaurantsRecyclerView = rule.activity.findViewById<RecyclerView>(R.id.restaurants_recycler_view)

        val recyclerViewSize = restaurantsRecyclerView.childCount

        assertThat(recyclerViewSize, equalTo(4))
    }

    @Test
    fun restaurantRecyclerViewClick() {
        onView(withId(R.id.restaurants_recycler_view))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))


        onView(withId(R.id.restaurant_name)).check(matches(withText("Diya")))
        onView(withId(R.id.restaurant_name_jp)).check(matches(withText("ディア")))

        val restaurantCategoriesRecyclerView = rule.activity.findViewById<RecyclerView>(R.id.restaurant_categories_recycler_view)
        val recyclerViewSize = restaurantCategoriesRecyclerView.childCount
        assertThat(recyclerViewSize, equalTo(2))
    }

    @Test
    fun categoryRecyclerViewIsCorrectSize() {
        val categoriesRecyclerView = rule.activity.findViewById<RecyclerView>(R.id.categories_recycler_view)

        val recyclerViewSize = categoriesRecyclerView.childCount

        assertThat(recyclerViewSize, equalTo(5))
    }

    @Test
    fun categoryRecyclerViewClick() {
        onView(withId(R.id.categories_recycler_view))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))


        onView(withId(R.id.restaurant_name)).check(matches(withText("Capri Cafe")))
        onView(withId(R.id.restaurant_name_jp)).check(matches(withText("カプリカフェ")))


        val restaurantsRecyclerView = rule.activity.findViewById<RecyclerView>(R.id.restaurants_recycler_view)
        val recyclerViewSize = restaurantsRecyclerView.childCount
        assertThat(recyclerViewSize, equalTo(1))
    }

    @Test
    fun multipleMarkerMapIsCreatedAndDisplayed() {
        val map = rule.activity.findViewById<MapView>(R.id.multiple_marker_map)

        assertThat(map.isClickable, equalTo(true))
        assertThat(map.isEnabled, equalTo(true))
        assertThat(map.isLaidOut, equalTo(true))
    }
}