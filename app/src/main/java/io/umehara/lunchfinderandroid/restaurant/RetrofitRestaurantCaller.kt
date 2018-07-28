package io.umehara.lunchfinderandroid.restaurant

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitRestaurantCaller {
    @GET("restaurants")
    fun getAll(): Call<List<Restaurant>>
}
