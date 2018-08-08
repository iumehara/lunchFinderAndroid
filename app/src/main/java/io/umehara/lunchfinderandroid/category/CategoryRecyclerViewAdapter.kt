package io.umehara.lunchfinderandroid.category

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.umehara.lunchfinderandroid.R

interface OnCategoryClickListener {
    fun onClick(category: Category, textView: TextView)
}

class CategoryRecyclerViewAdapter(
        private val categories: List<Category>,
        private val clickListener: OnCategoryClickListener? = null
) : Adapter<ViewHolder>() {
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
        val category = categories[position]
        categoryNameTextView.text = category.name
        if (clickListener != null) {
            categoryNameTextView.setOnClickListener({ _ -> clickListener.onClick(category, categoryNameTextView) })
        }
    }

    fun setOnRecyclerView(context: Context, recyclerView: RecyclerView) {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val categoryRecyclerViewAdapter = this
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = categoryRecyclerViewAdapter
        }
    }
}
