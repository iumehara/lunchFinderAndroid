package io.umehara.lunchfinderandroid.restaurant

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitRestaurantCaller {
    @GET("restaurants/full")
    fun getAll(): Call<List<Restaurant>>
}
