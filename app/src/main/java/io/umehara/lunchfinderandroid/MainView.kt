package io.umehara.lunchfinderandroid

import io.umehara.lunchfinderandroid.category.Category
import io.umehara.lunchfinderandroid.map.MultipleMarkerMap
import io.umehara.lunchfinderandroid.restaurant.Restaurant

interface MainView {
    fun setupRestaurantList()
    fun setupCategoryList()
    fun updateRestaurantList(restaurants: List<Restaurant>)
    fun updateCategoryList(categories: List<Category>)
    fun setMap(multipleMarkerMap: MultipleMarkerMap)
    fun setRestaurantDetails(restaurant: Restaurant)
}
