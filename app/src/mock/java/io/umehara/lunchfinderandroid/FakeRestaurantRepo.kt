package io.umehara.lunchfinderandroid

import io.reactivex.Single
import io.umehara.lunchfinderandroid.category.Category
import io.umehara.lunchfinderandroid.map.Geolocation
import io.umehara.lunchfinderandroid.restaurant.Restaurant
import io.umehara.lunchfinderandroid.restaurant.RestaurantRepo
import java.math.BigDecimal
import java.util.*

class FakeRestaurantRepo: RestaurantRepo  {
    val restaurants = Arrays.asList(
        Restaurant(
                1,
                "Afuri",
                "あふり",
                "www.afuri.example.com",
                Geolocation(BigDecimal(1.11), BigDecimal(1.11)),
                Arrays.asList(Category(3, "ramen"))
        ),
        Restaurant(
                2,
                "Butagumi",
                "豚組",
                "www.butagumi.example.com",
                Geolocation(BigDecimal(2.22), BigDecimal(2.22)),
                Arrays.asList(Category(5, "tonkatsu"))
        ),
        Restaurant(
                3,
                "Capri Cafe",
                "カプリカフェ",
                "www.capri.example.com",
                Geolocation(BigDecimal(3.33), BigDecimal(3.33)),
                Arrays.asList(Category(2, "pasta"))
        ),
        Restaurant(
                4,
                "Diya",
                "ディア",
                "www.diya.example.com",
                Geolocation(BigDecimal(4.44), BigDecimal(4.44)),
                Arrays.asList(Category(1, "curry"), Category(4, "spicy"))
        )
    )

    override fun getAll(): Single<List<Restaurant>> {
        return Single.create { observer -> observer.onSuccess(restaurants)}
    }

    override fun getWhere(categoryId: Long): Single<List<Restaurant>> {
        val filteredRestaurants = restaurants.filter { r -> r.categories.map { c -> c.id }.contains(categoryId) }

        return Single.create { observer -> observer.onSuccess(filteredRestaurants)}
    }
}