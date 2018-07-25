package io.umehara.lunchfinderandroid

import java.util.Arrays.asList

class RestaurantListPresenter {
    private lateinit var restaurantListView: RestaurantListView

    fun setView(restaurantListView: RestaurantListView) {
        this.restaurantListView = restaurantListView
    }

    fun onCreate() {
        val restaurants = asList(
                Restaurant(1, "First", "一"),
                Restaurant(2, "Second", "二"),
                Restaurant(3, "Third", "三")
        )

        restaurantListView.setRow(restaurants)
    }
}
