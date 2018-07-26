package io.umehara.lunchfinderandroid

import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    fun providesRestaurantListView(mainActivity: MainActivity): MainView {
        return mainActivity
    }

    @Provides
    fun providesRestaurantListPresenter(mainView: MainView): MainPresenter {
        return DefaultMainPresenter(mainView)
    }
}