package io.umehara.lunchfinderandroid

import dagger.Module
import dagger.Provides
import io.umehara.lunchfinderandroid.restaurant.DefaultRestaurantRepo
import io.umehara.lunchfinderandroid.restaurant.RestaurantRepo

@Module
class MainActivityModule {
    @Provides
    fun providesRestaurantListView(mainActivity: MainActivity): MainView {
        return mainActivity
    }

    @Provides
    fun providesRestaurantListPresenter(mainView: MainView, repo: RestaurantRepo): MainPresenter {
        return DefaultMainPresenter(mainView, repo)
    }

    @Provides
    fun providesRestaurantRepo(): RestaurantRepo {
        return DefaultRestaurantRepo()
    }
}