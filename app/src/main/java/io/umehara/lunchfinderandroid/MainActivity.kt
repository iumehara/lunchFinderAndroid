package io.umehara.lunchfinderandroid

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.android.support.DaggerAppCompatActivity
import io.umehara.lunchfinderandroid.restaurant.Restaurant
import io.umehara.lunchfinderandroid.restaurant.RestaurantRecyclerViewAdapter
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), MainView, OnMapReadyCallback {
    private lateinit var map: MapView
    private lateinit var allRestaurants: List<Restaurant>

    @Inject
    lateinit var presenter: MainPresenter

    interface OnRestaurantClickListener {
        fun onClick(restaurant: Restaurant)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        presenter.getRestaurants()

        map = findViewById(R.id.multiple_marker_map)

        map.onCreate(savedInstanceState)
    }

    override fun setRow(restaurants: List<Restaurant>) {
        allRestaurants = restaurants
        val restaurantRecyclerView = findViewById<RecyclerView>(R.id.restaurant_recycler_view)
        val linearLayoutManager = LinearLayoutManager(this)
        val recyclerViewAdapter = RestaurantRecyclerViewAdapter(
                allRestaurants,
                object : OnRestaurantClickListener {
                    override fun onClick(restaurant: Restaurant) {
                        setDetail(restaurant)
                    }
                }
        )

        restaurantRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = recyclerViewAdapter
        }

        map.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        val startingPoint = LatLng(35.660480, 139.729247)
        val startingMarker = MarkerOptions().position(startingPoint).title("Roppongi Hills")
        map.addMarker(startingMarker)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startingPoint, 16.0F))
        allRestaurants.forEach({
            val geolocation = it.geolocation
            if (geolocation != null) {
                val marker = MarkerOptions()
                        .position(LatLng(geolocation.lat.toDouble(), geolocation.long.toDouble()))
                        .title(it.name)
                map.addMarker(marker)
            }
        })
    }

    override fun setDetail(restaurant: Restaurant) {
        val restaurantNameLabel = findViewById<TextView>(R.id.restaurant_name)
        restaurantNameLabel.text = restaurant.name

        val restaurantNameJpLabel = findViewById<TextView>(R.id.restaurant_name_jp)
        restaurantNameJpLabel.text = restaurant.nameJp
    }

    override fun onStart() {
        super.onStart()
        map.onStart()
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onStop() {
        super.onStop()
        map.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        map.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        map.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map.onLowMemory()
    }
}
