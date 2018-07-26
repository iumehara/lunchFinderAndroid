package io.umehara.lunchfinderandroid

import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    fun providesRestaurantListView(mainActivity: MainActivity): RestaurantListView {
        return mainActivity
    }

    @Provides
    fun providesRestaurantListPresenter(restaurantListView: RestaurantListView): RestaurantListPresenter {
        return DefaultRestaurantListPresenter(restaurantListView)
    }
}