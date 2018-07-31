package io.umehara.lunchfinderandroid.restaurant

import io.umehara.lunchfinderandroid.category.Category
import io.umehara.lunchfinderandroid.Geolocation

data class Restaurant(
        val id: Int,
        val name: String,
        val nameJp: String,
        val website: String?,
        val geolocation: Geolocation?,
        val categories: List<Category>
)