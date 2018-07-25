package io.umehara.lunchfinderandroid

class FakeRestaurantListView: RestaurantListView {
    var setRowArguments: List<Restaurant>? = null
    override fun setRow(restaurants: List<Restaurant>) {
        setRowArguments = restaurants
    }
}