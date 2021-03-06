package io.umehara.lunchfinderandroid.restaurant

import io.reactivex.Single
import java.util.Arrays.asList

class SuccessStubRestaurantRepo: RestaurantRepo {
    override fun getAll(): Single<List<Restaurant>> {
        return Single.create { observer ->
            val restaurants = asList(
                    Restaurant(1, "First", "一", "", null, emptyList()),
                    Restaurant(2, "Second", "二", "", null, emptyList()),
                    Restaurant(3, "Third", "三", "", null, emptyList())
            )
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
