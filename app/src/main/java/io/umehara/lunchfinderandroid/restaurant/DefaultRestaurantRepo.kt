package io.umehara.lunchfinderandroid.restaurant

import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class DefaultRestaurantRepo @Inject constructor(private val restaurantCaller: RetrofitRestaurantCaller): RestaurantRepo {

    override fun getAll(): Single<List<Restaurant>> {
        return Single.create { observer ->
            restaurantCaller.getAll().enqueue(object: Callback<List<Restaurant>> {
                override fun onResponse(call: Call<List<Restaurant>>?, response: Response<List<Restaurant>>?) {
                    if (response == null || !response.isSuccessful || response.body() == null) {
                        observer.onError(IOException())
                        return
                    }

                    val restaurants = response.body() ?: return
                    observer.onSuccess(restaurants)
                }

                override fun onFailure(call: Call<List<Restaurant>>?, t: Throwable?) {
                    if (t != null) observer.onError(IOException(t.message))
                }
            })
        }
    }
}
