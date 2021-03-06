package io.umehara.lunchfinderandroid

import dagger.Module
import dagger.Provides
import io.umehara.lunchfinderandroid.category.CategoryRepo
import io.umehara.lunchfinderandroid.category.DefaultCategoryRepo
import io.umehara.lunchfinderandroid.category.RetrofitCategoryCaller
import io.umehara.lunchfinderandroid.map.DefaultMultipleMarkerMap
import io.umehara.lunchfinderandroid.map.MultipleMarkerMap
import io.umehara.lunchfinderandroid.restaurant.DefaultRestaurantRepo
import io.umehara.lunchfinderandroid.restaurant.RestaurantRepo
import io.umehara.lunchfinderandroid.restaurant.RetrofitRestaurantCaller
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    fun providesCategoryRepo(categoryCaller: RetrofitCategoryCaller): CategoryRepo {
        return DefaultCategoryRepo(categoryCaller)
    }

    @Provides
    fun providesRestaurantRepo(restaurantCaller: RetrofitRestaurantCaller): RestaurantRepo {
        return DefaultRestaurantRepo(restaurantCaller)
    }

    @Provides
    fun providesCategoryCaller(): RetrofitCategoryCaller {
        return Retrofit
                .Builder()
                .baseUrl("https://lunch-finder-api.cfapps.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitCategoryCaller::class.java)
    }

    @Provides
    fun providesRestaurantCaller(): RetrofitRestaurantCaller {
        return Retrofit
                .Builder()
                .baseUrl("https://lunch-finder-api.cfapps.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitRestaurantCaller::class.java)
    }

    @Provides
    fun providesMultipleMarkerMap(): MultipleMarkerMap {
        return DefaultMultipleMarkerMap()
    }
}