package io.umehara.lunchfinderandroid

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.android.support.DaggerAppCompatActivity
import io.umehara.lunchfinderandroid.restaurant.Restaurant
import javax.inject.Inject


class MainActivity: DaggerAppCompatActivity(), MainView, OnMapReadyCallback {
    private lateinit var map: MapView

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        presenter.getRestaurants()

        map = findViewById(R.id.multiple_marker_map)

        map.onCreate(savedInstanceState)
        map.getMapAsync(this)
    }

    override fun setRow(restaurants: List<Restaurant>) {
        val adapterRestaurants: ArrayAdapter<String> = ArrayAdapter(
                applicationContext,
                android.R.layout.simple_list_item_1,
                restaurants.map(Restaurant::name)
        )

        val restaurantList: ListView = findViewById(R.id.restaurant_list)

        restaurantList.adapter = adapterRestaurants
        val clickListener = OnItemClickListener({ _, _, position, _ -> setDetail(restaurants[position]) })
        restaurantList.onItemClickListener = clickListener
    }

    override fun onMapReady(map: GoogleMap) {
        val startingPoint = LatLng(35.660480, 139.729247)
        val startingMarker = MarkerOptions().position(startingPoint).title("Roppongi Hills")
        map.addMarker(startingMarker)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startingPoint, 15.0F))
    }

    override fun setDetail(restaurant: Restaurant) {
        val restaurantNameLabel = findViewById<TextView>(R.id.restaurant_name)
        restaurantNameLabel.text = restaurant.name
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
