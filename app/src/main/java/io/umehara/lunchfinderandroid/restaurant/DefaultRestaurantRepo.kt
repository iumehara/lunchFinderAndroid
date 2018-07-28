package io.umehara.lunchfinderandroid.restaurant

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class DefaultRestaurantRepo: RestaurantRepo {
    private var restaurantCaller: RetrofitRestaurantCaller = Retrofit
            .Builder()
            .baseUrl("https://lunch-finder-api.cfapps.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitRestaurantCaller::class.java)

    override fun getAll(): Single<List<Restaurant>> {
        val restaurantsSingle: Single<List<Restaurant>> = Single.create { observer ->
            restaurantCaller.getAll().enqueue(object : Callback<List<Restaurant>> {
                override fun onResponse(call: Call<List<Restaurant>>?, response: Response<List<Restaurant>>?) {
                    if (response == null || !response.isSuccessful || response.body() == null) {
                        observer.onError(IOException())
                        return
                    }

                    val restaurants = response.body() ?: return
                    observer.onSuccess(restaurants)
                }

                override fun onFailure(call: Call<List<Restaurant>>?, t: Throwable?) {
                    if (t != null) {
                        observer.onError(t)
                    }
                }
            })
        }

        return restaurantsSingle.observeOn(AndroidSchedulers.mainThread())
    }
}
