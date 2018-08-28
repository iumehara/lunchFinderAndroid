package io.umehara.lunchfinderandroid

import io.umehara.lunchfinderandroid.category.Category
import io.umehara.lunchfinderandroid.map.MultipleMarkerMap
import io.umehara.lunchfinderandroid.restaurant.Restaurant

class FakeMainView : MainView {
    var restaurantListIsSetup = false
    override fun setupRestaurantList() {
        restaurantListIsSetup = true
    }

    var categoryListIsSetup = false
    override fun setupCategoryList() {
        categoryListIsSetup = true
    }

    var updateRestaurantListArguments: List<Restaurant>? = null
    override fun updateRestaurantList(restaurants: List<Restaurant>) {
        if (restaurantListIsSetup) {
            updateRestaurantListArguments = restaurants
        }
    }

    var updateCategoryListArguments: List<Category>? = null
    override fun updateCategoryList(categories: List<Category>) {
        if (categoryListIsSetup) {
            updateCategoryListArguments = categories
        }
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
