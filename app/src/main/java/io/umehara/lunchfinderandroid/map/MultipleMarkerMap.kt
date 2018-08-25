package io.umehara.lunchfinderandroid.map

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import io.umehara.lunchfinderandroid.restaurant.Restaurant

interface MultipleMarkerMap : OnMapReadyCallback {
    override fun onMapReady(var1: GoogleMap)
    fun setMarkers(restaurants: List<Restaurant>)
    fun updateRestaurants(restaurants: List<Restaurant>)
    fun updateMarker(geolocation: Geolocation?)
}