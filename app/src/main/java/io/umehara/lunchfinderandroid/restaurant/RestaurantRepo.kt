package io.umehara.lunchfinderandroid.restaurant

import io.reactivex.Single

interface RestaurantRepo {
    fun getAll(): Single<List<Restaurant>>
    fun getWhere(categoryId: Long): Single<List<Restaurant>>
}
