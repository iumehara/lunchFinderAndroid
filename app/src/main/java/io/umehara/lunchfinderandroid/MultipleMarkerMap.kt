package io.umehara.lunchfinderandroid

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.umehara.lunchfinderandroid.restaurant.Restaurant

class MultipleMarkerMap(private val restaurants: List<Restaurant>) : OnMapReadyCallback {
    override fun onMapReady(map: GoogleMap) {
        val startingPoint = LatLng(35.660480, 139.729247)
        val startingMarker = MarkerOptions().position(startingPoint).title("Roppongi Hills")
        map.addMarker(startingMarker)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startingPoint, 16.0F))
        restaurants.forEach({
            val geolocation = it.geolocation
            if (geolocation != null) {
                val marker = MarkerOptions()
                        .position(LatLng(geolocation.lat.toDouble(), geolocation.long.toDouble()))
                        .title(it.name)
                map.addMarker(marker)
            }
        })
    }
}