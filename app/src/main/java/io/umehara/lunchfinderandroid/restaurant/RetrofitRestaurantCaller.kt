package io.umehara.lunchfinderandroid.restaurant

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitRestaurantCaller {
    @GET("restaurants/full")
    fun getAll(): Call<List<Restaurant>>

    @GET("categories/{categoryId}/restaurants/full")
    fun getWhere(@Path("categoryId") categoryId: Long): Call<List<Restaurant>>
}
