package io.umehara.lunchfinderandroid

import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides fun providesRestaurantListPresenter(): RestaurantListPresenter {
        return DefaultRestaurantListPresenter()
    }
}