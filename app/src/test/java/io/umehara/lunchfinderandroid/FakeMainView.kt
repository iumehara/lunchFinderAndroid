package io.umehara.lunchfinderandroid

import io.umehara.lunchfinderandroid.restaurant.Restaurant

class FakeMainView: MainView {
    var setRowArguments: List<Restaurant>? = null
    override fun setRow(restaurants: List<Restaurant>) {
        setRowArguments = restaurants
    }

    var setDetailArgument: Restaurant? = null
    override fun setDetail(restaurant: Restaurant) {
        setDetailArgument = restaurant
    }
}