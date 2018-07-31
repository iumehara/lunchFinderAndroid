package io.umehara.lunchfinderandroid.category

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.umehara.lunchfinderandroid.R

class CategoryRecyclerViewAdapter(private val categories: List<Category>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_recycler_view_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val categoryNameTextView = holder.itemView.findViewById<TextView>(R.id.row_category_name)
        categoryNameTextView.text = categories[position].name
    }
}