package io.umehara.lunchfinderandroid

import io.reactivex.Single
import io.umehara.lunchfinderandroid.category.Category
import io.umehara.lunchfinderandroid.category.CategoryRepo
import java.util.Arrays.asList

class StubCategoryRepo : CategoryRepo {
    override fun getAll(): Single<List<Category>> {
        return Single.create { observer ->
            val categories = asList(
                    Category(1, "curry"),
                    Category(2, "pasta"),
                    Category(3, "ramen"),
                    Category(4, "spicy"),
                    Category(5, "tonkatsu")

            )
            observer.onSuccess(categories)
        }
    }
}
