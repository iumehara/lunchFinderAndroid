package io.umehara.lunchfinderandroid

import io.reactivex.disposables.CompositeDisposable
import io.umehara.lunchfinderandroid.restaurant.RestaurantRepo
import javax.inject.Inject

class DefaultMainPresenter @Inject constructor(private val view: MainView,
                                               private val repo: RestaurantRepo): MainPresenter {
    private var compositeDisposable = CompositeDisposable()

    override fun getRestaurants() {
        val disposable = repo
                .getAll()
                .subscribe(
                        { restaurants -> view.setRow(restaurants) },
                        { error -> println("Error" + error.message) }
                )
        compositeDisposable.add(disposable)
    }
}
