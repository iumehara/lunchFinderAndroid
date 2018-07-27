package io.umehara.lunchfinderandroid

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

class DefaultMainPresenter @Inject constructor(private val mainView: MainView): MainPresenter {
    override fun getRestaurants() {
        val defaultRestaurantRepo = DefaultRestaurantRepo()
        val callback = defaultRestaurantRepo.getAll()

        callback.enqueue(object: Callback<List<Restaurant>> {
            override fun onFailure(call: Call<List<Restaurant>>?, t: Throwable?) {
                if (t != null) { println("getRestaurant request failed:" + t.message.toString()) }
            }

            override fun onResponse(call: Call<List<Restaurant>>?, response: Response<List<Restaurant>>?) {
                if (response == null) { println("getRestaurant request received no response"); return }
                if (!response.isSuccessful) { println("getRestaurant request received unsuccessful response: " + response.message()); return }

                val restaurants = response.body() ?: return

                mainView.setRow(restaurants)
            }
        })
    }
}

class DefaultRestaurantRepo: RetrofitRestaurantCaller {
    private var retrofitRestaurantCaller: RetrofitRestaurantCaller

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofitRestaurantCaller = retrofit.create(RetrofitRestaurantCaller::class.java)
    }

    override fun getAll(): Call<List<Restaurant>> {
        return retrofitRestaurantCaller.getAll()
    }
}

interface RetrofitRestaurantCaller {
    @GET("restaurants")
    fun getAll(): Call<List<Restaurant>>
}
