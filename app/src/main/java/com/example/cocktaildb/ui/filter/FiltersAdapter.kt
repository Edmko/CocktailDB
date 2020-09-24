package com.example.cocktaildb.ui.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktaildb.R
import com.example.cocktaildb.data.entity.Filters
import kotlinx.android.synthetic.main.filter_item.view.*

class FiltersAdapter : RecyclerView.Adapter<FiltersAdapter.FiltersViewHolder>() {
    private var checkedFilters = mutableSetOf<String>()
    private val filters = mutableListOf<String>()

    fun setData(filters: Filters?, checkedFilters: MutableSet<String>) {
        this.filters.clear()
        this.checkedFilters.clear()
        filters?.drinks?.forEach { drinkX -> this.filters.add(drinkX.strCategory) }
        if (checkedFilters.isEmpty()) {
            this.checkedFilters.addAll(this.filters)
        } else {
            this.checkedFilters.addAll(checkedFilters)
        }
        notifyDataSetChanged()
    }

    fun getListOfFilters(): MutableSet<String> {
        val listOfFilters = mutableSetOf<String>()
        filters.forEach { filter -> if (checkedFilters.contains(filter)) listOfFilters.add(filter) }
        return listOfFilters
    }

    override fun getItemCount(): Int = filters.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiltersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.filter_item, parent, false)
        return FiltersViewHolder(view)
    }

    override fun onBindViewHolder(holder: FiltersViewHolder, position: Int) {
        holder.bindTo(filters[position])
    }


    inner class FiltersViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {

        fun bindTo(drinkX: String) {
            itemView.apply {
                filter_name.text = drinkX
                checkbox.isChecked = checkedFilters.contains(drinkX)

                this.setOnClickListener {
                    checkbox.isChecked = !checkbox.isChecked
                    if (checkbox.isChecked) {
                        checkedFilters.add(it.filter_name.text.toString())

                    } else {
                        checkedFilters.remove(it.filter_name.text.toString())
                    }

                }
            }
        }

    }
}