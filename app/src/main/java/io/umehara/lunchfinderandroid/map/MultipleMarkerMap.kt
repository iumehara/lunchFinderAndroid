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
    private lateinit var googleMap: GoogleMap

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val startingPoint = LatLng(35.660480, 139.729247)
        val startingMarker = MarkerOptions().position(startingPoint).title("Roppongi Hills")
        googleMap.addMarker(startingMarker)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startingPoint, 16.0F))
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
        map.setOnMarkerClickListener({
            setCurrent(it)
            false
        })
    }

    fun updateMarker(geolocation: Geolocation?) {
        if (geolocation == null) return

        val markerToUpdate = markers.firstOrNull {
            it.position.latitude == geolocation.lat.toDouble() &&
                    it.position.longitude == geolocation.long.toDouble()
        }

        if (markerToUpdate != null) {
            setCurrent(markerToUpdate)
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(markerToUpdate.position), 500, null)
        }
    }

    private fun setCurrent(marker: Marker) {
        unsetCurrent(currentMarker)

        marker.showInfoWindow()
        marker.alpha = 1.0f
        currentMarker = marker
    }

    private fun unsetCurrent(marker: Marker?) {
        if (marker != null) {
            marker.hideInfoWindow()
            marker.alpha = 0.3f
        }
    }
}