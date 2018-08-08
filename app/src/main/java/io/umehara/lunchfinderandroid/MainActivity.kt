package io.umehara.lunchfinderandroid

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.google.android.gms.maps.MapView
import dagger.android.support.DaggerAppCompatActivity
import io.umehara.lunchfinderandroid.category.Category
import io.umehara.lunchfinderandroid.category.CategoryRecyclerViewAdapter
import io.umehara.lunchfinderandroid.category.OnCategoryClickListener
import io.umehara.lunchfinderandroid.map.MultipleMarkerMap
import io.umehara.lunchfinderandroid.restaurant.OnRestaurantClickListener
import io.umehara.lunchfinderandroid.restaurant.Restaurant
import io.umehara.lunchfinderandroid.restaurant.RestaurantRecyclerViewAdapter
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), MainView {
    @Inject
    lateinit var presenter: MainPresenter
    private lateinit var mapView: MapView
    private lateinit var multipleMarkerMap: MultipleMarkerMap
    private var selectedCategoryTextView: TextView? = null
    private var selectedRestaurantTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        presenter.getRestaurants()
        presenter.getCategories()

        mapView = findViewById(R.id.multiple_marker_map)
        mapView.onCreate(savedInstanceState)
    }

    override fun setCategoryList(categories: List<Category>) {
        val categoryRecyclerView = findViewById<RecyclerView>(R.id.categories_recycler_view)
        val categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(
                categories,
                object : OnCategoryClickListener {
                    override fun onClick(category: Category, textView: TextView) {
                        presenter.selectCategory(category.id)

                        if (selectedCategoryTextView != null) {
                            unselectCategoryTextView(selectedCategoryTextView!!)
                        }
                        selectCategoryTextView(textView)
                    }
                }
        )
        categoryRecyclerViewAdapter.setOnRecyclerView(this, categoryRecyclerView)
    }

    override fun setRestaurantList(restaurants: List<Restaurant>) {
        val restaurantRecyclerView = findViewById<RecyclerView>(R.id.restaurants_recycler_view)

        val recyclerViewAdapter = RestaurantRecyclerViewAdapter(
                restaurants,
                object : OnRestaurantClickListener {
                    override fun onClick(restaurant: Restaurant, textView: TextView) {
                        setDetail(restaurant)
                        multipleMarkerMap.updateMarker(restaurant.geolocation)

                        if (selectedRestaurantTextView != null) {
                            unselectRestaurantTextView(selectedRestaurantTextView!!)
                        }
                        selectRestaurantTextView(textView)
                    }
                }
        )
        recyclerViewAdapter.setOnRecyclerView(this, restaurantRecyclerView)
    }

    override fun setMap(restaurants: List<Restaurant>) {
        multipleMarkerMap = MultipleMarkerMap(restaurants)
        mapView.getMapAsync(multipleMarkerMap)
    }

    override fun updateMap(restaurants: List<Restaurant>) {
        multipleMarkerMap.updateRestaurants(restaurants)
    }

    override fun setDetail(restaurant: Restaurant) {
        val restaurantNameLabel = findViewById<TextView>(R.id.restaurant_name)
        restaurantNameLabel.text = restaurant.name

        val restaurantNameJpLabel = findViewById<TextView>(R.id.restaurant_name_jp)
        restaurantNameJpLabel.text = restaurant.nameJp

        val categoryRecyclerView = findViewById<RecyclerView>(R.id.restaurant_categories_recycler_view)
        val categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(restaurant.categories)
        categoryRecyclerViewAdapter.setOnRecyclerView(this, categoryRecyclerView)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    private fun selectCategoryTextView(textView: TextView) {
        textView.setBackgroundColor(Color.rgb(242, 153, 74))
        selectedCategoryTextView = textView
    }

    private fun unselectCategoryTextView(textView: TextView) {
        textView.setBackgroundColor(Color.rgb(239, 199, 164))
    }

    private fun selectRestaurantTextView(textView: TextView) {
        textView.setBackgroundColor(Color.rgb(240, 240, 240))
        selectedRestaurantTextView = textView
    }

    private fun unselectRestaurantTextView(textView: TextView) {
        textView.setBackgroundColor(Color.rgb(255, 255, 255))
    }
}
