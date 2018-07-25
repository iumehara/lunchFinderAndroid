package io.umehara.lunchfinderandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity: AppCompatActivity(), RestaurantListView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = RestaurantListPresenter()
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
