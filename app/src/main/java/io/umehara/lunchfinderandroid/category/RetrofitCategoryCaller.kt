package io.umehara.lunchfinderandroid.category

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitCategoryCaller {
    @GET("categories")
    fun getAll(): Call<List<Category>>
}
