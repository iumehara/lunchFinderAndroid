package io.umehara.lunchfinderandroid

import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity: DaggerAppCompatActivity(), MainView {
    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.getRestaurants()
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

    override fun setDetail(restaurant: Restaurant) {
        val restaurantNameLabel = findViewById<TextView>(R.id.restaurant_name)
        restaurantNameLabel.text = restaurant.name
    }
}
