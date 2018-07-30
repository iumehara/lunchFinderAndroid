package io.umehara.lunchfinderandroid

import dagger.Module
import dagger.Provides
import io.umehara.lunchfinderandroid.restaurant.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    fun providesRestaurantRepo(restaurantCaller: RetrofitRestaurantCaller): RestaurantRepo {
        return DefaultRestaurantRepo(restaurantCaller)
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
}