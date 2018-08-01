package io.umehara.lunchfinderandroid.restaurant

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.umehara.lunchfinderandroid.MainActivity
import io.umehara.lunchfinderandroid.R


class RestaurantRecyclerViewAdapter(private val restaurants: List<Restaurant>,
                                    private val clickListener: MainActivity.OnRestaurantClickListener) : Adapter<ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val restaurantNameTextView = holder.itemView.findViewById<TextView>(R.id.row_restaurant_name)
        val restaurant = restaurants[position]
        restaurantNameTextView.text = restaurant.name
        restaurantNameTextView.setOnClickListener({ _ -> clickListener.onClick(restaurant) })
    }

    fun setOnRecyclerView(context: Context, recyclerView: RecyclerView) {
        val linearLayoutManager = LinearLayoutManager(context)
        val recyclerViewAdapter = this
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = recyclerViewAdapter
        }

        val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}
