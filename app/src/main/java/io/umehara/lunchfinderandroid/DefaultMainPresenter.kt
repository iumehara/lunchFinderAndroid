package io.umehara.lunchfinderandroid

import java.util.Arrays.asList
import javax.inject.Inject

class DefaultMainPresenter @Inject constructor(private val mainView: MainView): MainPresenter {
    override fun getRestaurants() {
        val restaurants = asList(
                Restaurant(1, "First", "一"),
                Restaurant(2, "Second", "二"),
                Restaurant(3, "Third", "三")
        )

        mainView.setRow(restaurants)
    }
}
