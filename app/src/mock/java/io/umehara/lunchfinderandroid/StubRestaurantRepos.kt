package io.umehara.lunchfinderandroid

import io.reactivex.Single
import io.umehara.lunchfinderandroid.category.Category
import io.umehara.lunchfinderandroid.map.Geolocation
import io.umehara.lunchfinderandroid.restaurant.Restaurant
import io.umehara.lunchfinderandroid.restaurant.RestaurantRepo
import java.math.BigDecimal
import java.util.Arrays.asList

class SuccessStubRestaurantRepo: RestaurantRepo {
    val restaurants = asList(
            Restaurant(
                    1,
                    "Afuri",
                    "あふり",
                    "www.afuri.example.com",
                    Geolocation(BigDecimal(1.11), BigDecimal(1.11)),
                    asList(Category(1, "ramen"))
            ),
            Restaurant(
                    2,
                    "Butagumi",
                    "豚組",
                    "www.butagumi.example.com",
                    Geolocation(BigDecimal(2.22), BigDecimal(2.22)),
                    asList(Category(2, "tonkatsu"))
            ),
            Restaurant(
                    3,
                    "Capri Cafe",
                    "カプリカフェ",
                    "www.capri.example.com",
                    Geolocation(BigDecimal(3.33), BigDecimal(3.33)),
                    asList(Category(3, "pasta"))
            )
    )



    override fun getAll(): Single<List<Restaurant>> {
        return Single.create { observer ->
            observer.onSuccess(restaurants)
        }
    }

    override fun getWhere(categoryId: Long): Single<List<Restaurant>> {
        return Single.create { observer ->
            val restaurants = asList(
                    Restaurant(1, "First", "一", "", null, emptyList()),
                    Restaurant(2, "Second", "二", "", null, emptyList()),
                    Restaurant(3, "Third", "三", "", null, emptyList())
            )
            observer.onSuccess(restaurants)
        }
    }
}

class EmptyStubRestaurantRepo: RestaurantRepo {
    override fun getAll(): Single<List<Restaurant>> {
        return Single.create { observer ->
            observer.onSuccess(emptyList())
        }
    }

    override fun getWhere(categoryId: Long): Single<List<Restaurant>> {
        return Single.create { observer ->
            observer.onSuccess(emptyList())
        }
    }
}
