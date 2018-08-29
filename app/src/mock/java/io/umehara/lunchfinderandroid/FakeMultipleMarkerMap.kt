package io.umehara.lunchfinderandroid

import com.google.android.gms.maps.GoogleMap
import io.umehara.lunchfinderandroid.map.Geolocation
import io.umehara.lunchfinderandroid.map.MultipleMarkerMap
import io.umehara.lunchfinderandroid.restaurant.Restaurant

class FakeMultipleMarkerMap : MultipleMarkerMap {
    var onMapReadyArguments: GoogleMap? = null
    override fun onMapReady(var1: GoogleMap) {
        onMapReadyArguments = var1
    }

    var setMarkersArguments: List<Restaurant>? = null
    override fun setMarkers(restaurants: List<Restaurant>) {
        setMarkersArguments = restaurants
    }

    var updateRestaurantsArguments: List<Restaurant>? = null
    override fun updateRestaurants(restaurants: List<Restaurant>) {
        updateRestaurantsArguments = restaurants
    }

    var updateMarkerArguments: Geolocation?? = null
    override fun updateMarker(geolocation: Geolocation?) {
        updateMarkerArguments = geolocation
    }
}