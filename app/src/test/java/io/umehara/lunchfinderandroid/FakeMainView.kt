package io.umehara.lunchfinderandroid

import io.umehara.lunchfinderandroid.restaurant.Restaurant

class FakeMainView: MainView {
    var setRestaurantListArguments: List<Restaurant>? = null
    override fun setRestaurantList(restaurants: List<Restaurant>) {
        setRestaurantListArguments = restaurants
    }

    var setMapArguments: List<Restaurant>? = null
    override fun setMap(restaurants: List<Restaurant>) {
        setMapArguments = restaurants
    }

    var setDetailArgument: Restaurant? = null
    override fun setDetail(restaurant: Restaurant) {
        setDetailArgument = restaurant
    }
}
