package io.umehara.lunchfinderandroid

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.umehara.lunchfinderandroid.category.CategoryRepo
import io.umehara.lunchfinderandroid.restaurant.RestaurantRepo
import javax.inject.Inject

class DefaultMainPresenter
@Inject constructor(
        private val view: MainView,
        private val restaurantRepo: RestaurantRepo,
        private val categoryRepo: CategoryRepo
): MainPresenter {
    private var compositeDisposable = CompositeDisposable()

    override fun getRestaurants() {
        val disposable = restaurantRepo
                .getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { restaurants ->
                            view.setMap(restaurants)
                            view.setRestaurantList(restaurants)
                            view.setDetail(restaurants[0])
                        },
                        { error -> println("Error" + error.message) }
                )
        compositeDisposable.add(disposable)
    }

    override fun getCategories() {
        val disposable = categoryRepo
                .getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { categories -> view.setCategoryList(categories) },
                        { error -> println("Error" + error.message) }
                )
        compositeDisposable.add(disposable)
    }

    override fun getCategoryRestaurants(categoryId: Long) {
        val disposable = restaurantRepo
                .getWhere(categoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { restaurants ->
                            view.setMap(restaurants)
                            view.setRestaurantList(restaurants)
                            view.setDetail(restaurants[0])
                        },
                        { error -> println("Error" + error.message) }
                )
        compositeDisposable.add(disposable)
    }
}
