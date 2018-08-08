package io.umehara.lunchfinderandroid

interface MainPresenter {
    fun getRestaurants()
    fun getCategories()
    fun selectCategory(categoryId: Long)
}
