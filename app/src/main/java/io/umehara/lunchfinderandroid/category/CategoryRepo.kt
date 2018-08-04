package io.umehara.lunchfinderandroid.category

import io.reactivex.Single

interface CategoryRepo {
    fun getAll(): Single<List<Category>>
}
