package io.umehara.lunchfinderandroid.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import io.umehara.lunchfinderandroid.restaurant.Restaurant

class MultipleMarkerMap(private val restaurants: List<Restaurant>) : OnMapReadyCallback {
    private var markers: MutableList<Marker> = mutableListOf()
    private var currentMarker: Marker? = null

    override fun onMapReady(map: GoogleMap) {
        val startingPoint = LatLng(35.660480, 139.729247)
        val startingMarker = MarkerOptions().position(startingPoint).title("Roppongi Hills")
        map.addMarker(startingMarker)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startingPoint, 16.0F))
        restaurants.forEach({
            val geolocation = it.geolocation
            if (geolocation != null) {
                val markerOptions = MarkerOptions()
                        .position(LatLng(geolocation.lat.toDouble(), geolocation.long.toDouble()))
                        .title(it.name)
                        .alpha(0.3f)

                val marker = map.addMarker(markerOptions)
                markers.add(marker)
            }
        })
    }

    fun updateMarker(geolocation: Geolocation?) {
        if (geolocation == null) return

        val markerToUpdate = markers.firstOrNull {
            it.position.latitude == geolocation.lat.toDouble() &&
                    it.position.longitude == geolocation.long.toDouble()
        }

        unsetCurrent(currentMarker)

        if (markerToUpdate != null) {
            currentMarker = setCurrent(markerToUpdate)
        }
    }

    private fun setCurrent(marker: Marker): Marker {
        marker.alpha = 1.0f
        return marker
    }

    private fun unsetCurrent(marker: Marker?) {
        if (marker != null) {
            marker.alpha = 0.3f
        }
    }
}