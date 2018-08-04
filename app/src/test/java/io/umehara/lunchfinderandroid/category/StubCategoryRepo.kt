package io.umehara.lunchfinderandroid.category

import io.reactivex.Single
import java.util.Arrays.asList

class StubCategoryRepo : CategoryRepo {
    override fun getAll(): Single<List<Category>> {
        return Single.create { observer ->
            val categories = asList(
                    Category(1, "Pizza", 0),
                    Category(2, "Sushi", 0)
            )
            observer.onSuccess(categories)
        }
    }
}
