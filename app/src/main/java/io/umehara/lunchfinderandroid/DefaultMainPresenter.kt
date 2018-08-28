package io.umehara.lunchfinderandroid

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.umehara.lunchfinderandroid.category.CategoryRepo
import io.umehara.lunchfinderandroid.map.MultipleMarkerMap
import io.umehara.lunchfinderandroid.restaurant.Restaurant
import io.umehara.lunchfinderandroid.restaurant.RestaurantRepo
import javax.inject.Inject

class DefaultMainPresenter
@Inject constructor(
        private val view: MainView,
        private val restaurantRepo: RestaurantRepo,
        private val categoryRepo: CategoryRepo,
        private val multipleMarkerMap: MultipleMarkerMap
) : MainPresenter {
    private var compositeDisposable = CompositeDisposable()

    override fun setup() {
        view.setupRestaurantList()
        view.setupCategoryList()
        view.setMap(multipleMarkerMap)
        getRestaurants()
        getCategories()
    }

    override fun selectCategory(categoryId: Long) {
        val disposable = restaurantRepo
                .getWhere(categoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { restaurants ->
                            multipleMarkerMap.updateRestaurants(restaurants)
                            view.updateRestaurantList(restaurants)
                            if (restaurants.isNotEmpty()) {
                                view.setRestaurantDetails(restaurants[0])
                            }
                        },
                        { error -> println("Error" + error.message) }
                )
        compositeDisposable.add(disposable)
    }

    override fun selectRestaurant(restaurant: Restaurant) {
        multipleMarkerMap.updateMarker(restaurant.geolocation)
        view.setRestaurantDetails(restaurant)
    }

    override fun selectAllRestaurants() {
        val disposable = restaurantRepo
                .getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { restaurants ->
                            view.updateRestaurantList(restaurants)
                            multipleMarkerMap.updateRestaurants(restaurants)
                        },
                        { error -> println("Error" + error.message) }
                )
        compositeDisposable.add(disposable)
    }

    private fun getRestaurants() {
        val disposable = restaurantRepo
                .getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { restaurants ->
                            multipleMarkerMap.setMarkers(restaurants)
                            view.updateRestaurantList(restaurants)
                            view.setRestaurantDetails(restaurants[0])
                        },
                        { error -> println("Error" + error.message) }
                )
        compositeDisposable.add(disposable)
    }

    private fun getCategories() {
        val disposable = categoryRepo
                .getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { categories -> view.updateCategoryList(categories) },
                        { error -> println("Error" + error.message) }
                )
        compositeDisposable.add(disposable)
    }
}
