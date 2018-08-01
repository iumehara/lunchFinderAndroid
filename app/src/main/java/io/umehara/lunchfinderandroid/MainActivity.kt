package io.umehara.lunchfinderandroid

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.google.android.gms.maps.MapView
import dagger.android.support.DaggerAppCompatActivity
import io.umehara.lunchfinderandroid.category.CategoryRecyclerViewAdapter
import io.umehara.lunchfinderandroid.map.MultipleMarkerMap
import io.umehara.lunchfinderandroid.restaurant.Restaurant
import io.umehara.lunchfinderandroid.restaurant.RestaurantRecyclerViewAdapter
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), MainView {
    private lateinit var mapView: MapView
    private lateinit var multipleMarkerMap: MultipleMarkerMap

    @Inject
    lateinit var presenter: MainPresenter

    interface OnRestaurantClickListener {
        fun onClick(restaurant: Restaurant)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        presenter.getRestaurants()

        mapView = findViewById(R.id.multiple_marker_map)
        mapView.onCreate(savedInstanceState)
    }

    override fun setRestaurantList(restaurants: List<Restaurant>) {
        val restaurantRecyclerView = findViewById<RecyclerView>(R.id.restaurant_recycler_view)
        val linearLayoutManager = LinearLayoutManager(this)
        val recyclerViewAdapter = RestaurantRecyclerViewAdapter(
                restaurants,
                object : OnRestaurantClickListener {
                    override fun onClick(restaurant: Restaurant) {
                        setDetail(restaurant)
                        multipleMarkerMap.updateMarker(restaurant.geolocation)
                    }
                }
        )

        restaurantRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = recyclerViewAdapter
        }
    }

    override fun setMap(restaurants: List<Restaurant>) {
        multipleMarkerMap = MultipleMarkerMap(restaurants)
        mapView.getMapAsync(multipleMarkerMap)
    }

    override fun setDetail(restaurant: Restaurant) {
        val restaurantNameLabel = findViewById<TextView>(R.id.restaurant_name)
        restaurantNameLabel.text = restaurant.name

        val restaurantNameJpLabel = findViewById<TextView>(R.id.restaurant_name_jp)
        restaurantNameJpLabel.text = restaurant.nameJp

        val categoryRecyclerView = findViewById<RecyclerView>(R.id.category_recycler_view)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(restaurant.categories)
        categoryRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = categoryRecyclerViewAdapter
        }
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
}
