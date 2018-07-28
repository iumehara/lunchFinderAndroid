package io.umehara.lunchfinderandroid.restaurant

import io.reactivex.Single
import java.util.Arrays.asList

class StubRestaurantRepo: RestaurantRepo {
    override fun getAll(): Single<List<Restaurant>> {
        return Single.create { observer ->
            val restaurants = asList(
                    Restaurant(1, "First", "一"),
                    Restaurant(2, "Second", "二"),
                    Restaurant(3, "Third", "三")
            )
            observer.onSuccess(restaurants)
        }
    }
}
