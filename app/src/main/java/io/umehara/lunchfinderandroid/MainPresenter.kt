package io.umehara.lunchfinderandroid

import io.umehara.lunchfinderandroid.restaurant.Restaurant

interface MainPresenter {
    fun setup()
    fun selectCategory(categoryId: Long)
    fun selectAllRestaurants()
    fun selectRestaurant(restaurant: Restaurant)
}
