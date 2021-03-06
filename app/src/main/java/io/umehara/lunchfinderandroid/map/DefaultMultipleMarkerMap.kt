package io.umehara.lunchfinderandroid.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import io.umehara.lunchfinderandroid.restaurant.Restaurant

class DefaultMultipleMarkerMap : MultipleMarkerMap {
    private var markers: MutableList<Marker> = mutableListOf()
    private var currentMarker: Marker? = null
    private lateinit var googleMap: GoogleMap

    override fun onMapReady(var1: GoogleMap) {
        this.googleMap = var1
        setStartingPointMarker(this.googleMap)
        var1.setOnMarkerClickListener { marker ->
            selectMarker(marker)
            false
        }
    }

    override fun updateRestaurants(restaurants: List<Restaurant>) {
        val restaurantPositions: List<LatLng?> = restaurants.map { restaurant ->
            positionFromGeolocation(restaurant.geolocation)
        }

        this.markers.forEach { marker ->
            marker.isVisible = restaurantPositions.contains(marker.position)
        }
    }

    override fun updateMarker(geolocation: Geolocation?) {
        val markerToUpdate = this.markers.firstOrNull { marker ->
            marker.position == positionFromGeolocation(geolocation)
        }

        if (markerToUpdate != null) {
            selectMarker(markerToUpdate)
            this.googleMap.animateCamera(
                    CameraUpdateFactory.newLatLng(markerToUpdate.position), 500, null
            )
        }
    }

    override fun setMarkers(restaurants: List<Restaurant>) {
        restaurants.forEach { restaurant ->
            val position = positionFromGeolocation(restaurant.geolocation) ?: return
            val markerOptions = MarkerOptions()
                    .position(position)
                    .title(restaurant.name)
                    .alpha(0.3f)
            val marker = this.googleMap.addMarker(markerOptions)
            markers.add(marker)
        }
    }

    private fun positionFromGeolocation(geolocation: Geolocation?): LatLng? {
        if (geolocation == null) return null

        return LatLng(geolocation.lat.toDouble(), geolocation.long.toDouble())
    }

    private fun setStartingPointMarker(map: GoogleMap) {
        val position = LatLng(35.660480, 139.729247)
        val markerOptions = MarkerOptions()
                .position(position)
                .title("Roppongi Hills")
        map.addMarker(markerOptions)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 16.0F))
    }

    private fun selectMarker(marker: Marker) {
        unselectMarker(currentMarker)

        marker.showInfoWindow()
        marker.alpha = 1.0f
        currentMarker = marker
    }

    private fun unselectMarker(marker: Marker?) {
        if (marker == null) return

        marker.hideInfoWindow()
        marker.alpha = 0.3f
    }
}
