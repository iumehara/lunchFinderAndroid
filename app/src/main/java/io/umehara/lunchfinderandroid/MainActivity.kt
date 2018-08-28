package io.umehara.lunchfinderandroid

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
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
    @Inject lateinit var presenter: MainPresenter
    private lateinit var mapView: MapView
    private var restaurantList: MutableList<Restaurant> = mutableListOf()
    private lateinit var restaurantRecyclerViewAdapter :RestaurantRecyclerViewAdapter
    private var categoryList: MutableList<Category> = mutableListOf()
    private lateinit var categoryRecyclerViewAdapter :CategoryRecyclerViewAdapter
    private var selectedCategoryTextView: TextView? = null
    private var selectedRestaurantTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        presenter.setup()
    }

    override fun setupRestaurantList() {
        restaurantRecyclerViewAdapter = RestaurantRecyclerViewAdapter(
                restaurantList,
                object : OnRestaurantClickListener {
                    override fun onClick(restaurant: Restaurant, textView: TextView) {
                        selectRestaurant(restaurant, textView)
                    }
                }
        )


        restaurantRecyclerViewAdapter.setOnRecyclerView(this, findViewById(R.id.restaurants_recycler_view))
    }

    override fun setupCategoryList() {
        categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(
                categoryList,
                object : OnCategoryClickListener {
                    override fun onClick(category: Category, textView: TextView) {
                        selectCategory(category, textView)
                    }
                }
        )
        categoryRecyclerViewAdapter.setOnRecyclerView(this, findViewById(R.id.categories_recycler_view))

        findViewById<Button>(R.id.all_categories).setOnClickListener {
            unHighlightSelectedCategoryTextView()
            selectedCategoryTextView = null
            presenter.selectAllRestaurants()
        }
    }

    override fun updateRestaurantList(restaurants: List<Restaurant>) {
        restaurantList.clear()
        restaurantList.addAll(restaurants)
        restaurantRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun updateCategoryList(categories: List<Category>) {
        categoryList.clear()
        categoryList.addAll(categories)
        categoryRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun setMap(multipleMarkerMap: MultipleMarkerMap) {
        mapView = findViewById(R.id.multiple_marker_map)
        mapView.onCreate(null)
        mapView.getMapAsync(multipleMarkerMap)
    }

    override fun setRestaurantDetails(restaurant: Restaurant) {
        findViewById<TextView>(R.id.restaurant_name).text = restaurant.name
        findViewById<TextView>(R.id.restaurant_name_jp).text = restaurant.nameJp
        CategoryRecyclerViewAdapter(restaurant.categories)
                .setOnRecyclerView(this, findViewById(R.id.restaurant_categories_recycler_view))
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

    private fun selectCategory(category: Category, textView: TextView) {
        presenter.selectCategory(category.id)
        selectCategoryTextView(textView)
    }

    private fun selectRestaurant(restaurant: Restaurant, textView: TextView) {
        presenter.selectRestaurant(restaurant)
        selectRestaurantTextView(textView)
    }

    private fun selectCategoryTextView(textView: TextView) {
        if (textView == selectedCategoryTextView) return
        unHighlightSelectedCategoryTextView()
        highlightCategoryTextView(textView)
        selectedCategoryTextView = textView
    }

    private fun selectRestaurantTextView(textView: TextView) {
        if (textView == selectedRestaurantTextView) return
        unHighlightSelectedRestaurantTextView()
        highlightRestaurantTextView(textView)
        selectedRestaurantTextView = textView
    }

    private fun unHighlightSelectedCategoryTextView() {
        selectedCategoryTextView?.setBackgroundColor(actionColor)
    }

    private fun highlightCategoryTextView(textView: TextView) {
        textView.setBackgroundColor(accentedActionColor)
    }

    private fun unHighlightSelectedRestaurantTextView() {
        selectedRestaurantTextView?.setBackgroundColor(backgroundColor)
    }

    private fun highlightRestaurantTextView(textView: TextView) {
        textView.setBackgroundColor(accentedBackgroundColor)
    }

    val actionColor = Color.rgb(239, 199, 164)
    val accentedActionColor = Color.rgb(242, 153, 74)
    val backgroundColor = Color.rgb(255, 255, 255)
    val accentedBackgroundColor = Color.rgb(240, 240, 240)
}
