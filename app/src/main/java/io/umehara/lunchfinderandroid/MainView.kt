package io.umehara.lunchfinderandroid

import io.umehara.lunchfinderandroid.category.Category
import io.umehara.lunchfinderandroid.restaurant.Restaurant

interface MainView {
    fun setCategoryList(categories: List<Category>)
    fun setRestaurantList(restaurants: List<Restaurant>)
    fun setMap(restaurants: List<Restaurant>)
    fun setDetail(restaurant: Restaurant)
}
