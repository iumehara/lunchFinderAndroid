package io.umehara.lunchfinderandroid

interface RestaurantListPresenter {
    fun setView(restaurantListView: RestaurantListView)
    fun onCreate()
}
