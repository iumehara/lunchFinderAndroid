package io.umehara.lunchfinderandroid

import io.umehara.lunchfinderandroid.restaurant.Restaurant

interface MainView {
    fun setRow(restaurants: List<Restaurant>)
    fun setDetail(restaurant: Restaurant)
}
