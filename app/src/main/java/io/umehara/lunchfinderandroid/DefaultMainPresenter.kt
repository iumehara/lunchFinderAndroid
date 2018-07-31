package io.umehara.lunchfinderandroid

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.umehara.lunchfinderandroid.restaurant.RestaurantRepo
import javax.inject.Inject

class DefaultMainPresenter @Inject constructor(private val view: MainView,
                                               private val repo: RestaurantRepo): MainPresenter {
    private var compositeDisposable = CompositeDisposable()

    override fun getRestaurants() {
        val disposable = repo
                .getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { restaurants ->
                            view.setRestaurantList(restaurants)
                            view.setMap(restaurants)
                        },
                        { error -> println("Error" + error.message) }
                )
        compositeDisposable.add(disposable)
    }
}
