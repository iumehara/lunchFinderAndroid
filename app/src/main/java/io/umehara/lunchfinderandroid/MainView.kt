package io.umehara.lunchfinderandroid

interface MainView {
    fun setRow(restaurants: List<Restaurant>)
    fun setDetail(restaurant: Restaurant)
}
