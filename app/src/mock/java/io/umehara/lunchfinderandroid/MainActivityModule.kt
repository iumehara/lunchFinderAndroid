package io.umehara.lunchfinderandroid

import dagger.Module
import dagger.Provides
import io.umehara.lunchfinderandroid.category.CategoryRepo
import io.umehara.lunchfinderandroid.map.MultipleMarkerMap
import io.umehara.lunchfinderandroid.restaurant.RestaurantRepo

@Module
class MainActivityModule {
    @Provides
    fun providesMainView(mainActivity: MainActivity): MainView {
        return mainActivity
    }

    @Provides
    fun providesMainPresenter(
            mainView: MainView,
            restaurantRepo: RestaurantRepo,
            categoryRepo: CategoryRepo,
            multipleMarkerMap: MultipleMarkerMap
    ): MainPresenter {
        return DefaultMainPresenter(mainView, restaurantRepo, categoryRepo, multipleMarkerMap)
    }

    @Provides
    fun providesCategoryRepo(): CategoryRepo {
        return StubCategoryRepo()
    }

    @Provides
    fun providesRestaurantRepo(): RestaurantRepo {
        return FakeRestaurantRepo()
    }

    @Provides
    fun providesMultipleMarkerMap(): MultipleMarkerMap {
        return FakeMultipleMarkerMap()
    }
}