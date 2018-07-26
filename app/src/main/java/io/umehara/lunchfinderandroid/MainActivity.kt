package io.umehara.lunchfinderandroid

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity: DaggerAppCompatActivity(), RestaurantListView {

    @Inject
    lateinit var presenter: RestaurantListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.setView(this)
        presenter.onCreate()
    }

    override fun setRow(restaurants: List<Restaurant>) {
        val adapterRestaurants: ArrayAdapter<String> = ArrayAdapter(
                applicationContext,
                android.R.layout.simple_list_item_1,
                restaurants.map(Restaurant::name)
        )

        val restaurantList: ListView = findViewById(R.id.restaurant_list)

        restaurantList.adapter = adapterRestaurants
    }
}
