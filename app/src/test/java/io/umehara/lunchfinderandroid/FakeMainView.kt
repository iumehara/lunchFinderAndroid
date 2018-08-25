package io.umehara.lunchfinderandroid

import io.umehara.lunchfinderandroid.category.Category
import io.umehara.lunchfinderandroid.map.MultipleMarkerMap
import io.umehara.lunchfinderandroid.restaurant.Restaurant

class FakeMainView : MainView {
    var setRestaurantListArguments: List<Restaurant>? = null
    override fun setRestaurantList(restaurants: List<Restaurant>) {
        setRestaurantListArguments = restaurants
    }

    var setCategoryListArguments: List<Category>? = null
    override fun setCategoryList(categories: List<Category>) {
        setCategoryListArguments = categories
    }

    var setMapArguments: MultipleMarkerMap? = null
    override fun setMap(multipleMarkerMap: MultipleMarkerMap) {
        setMapArguments = multipleMarkerMap
    }

    var setRestaurantDetailArgument: Restaurant? = null
    override fun setRestaurantDetails(restaurant: Restaurant) {
        setRestaurantDetailArgument = restaurant
    }
}
