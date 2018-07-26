package io.umehara.lunchfinderandroid

import java.util.Arrays.asList
import javax.inject.Inject

class DefaultRestaurantListPresenter @Inject constructor(private val restaurantListView: RestaurantListView): RestaurantListPresenter {
    override fun getRestaurants() {
        val restaurants = asList(
                Restaurant(1, "First", "一"),
                Restaurant(2, "Second", "二"),
                Restaurant(3, "Third", "三")
        )

        restaurantListView.setRow(restaurants)
    }
}
