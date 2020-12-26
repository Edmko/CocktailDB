package com.example.cocktaildb.ui.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktaildb.R
import com.example.cocktaildb.data.entity.Filter
import kotlinx.android.synthetic.main.filter_item.view.*

class FiltersAdapter : RecyclerView.Adapter<FiltersAdapter.FiltersViewHolder>() {
    private var checkedFilters = mutableListOf<Filter>()
    private val filters = mutableListOf<Filter>()

    fun setData(filters: List<Filter>, checkedFilters: List<Filter>) {
        this.filters.clear()
        this.checkedFilters.clear()
        this.filters.addAll(filters)
        this.checkedFilters.addAll(checkedFilters)
        notifyDataSetChanged()
    }

    fun getListOfFilters() = checkedFilters

    override fun getItemCount(): Int = filters.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiltersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.filter_item, parent, false)
        return FiltersViewHolder(view)
    }

    override fun onBindViewHolder(holder: FiltersViewHolder, position: Int) {
        holder.bindTo(filters[position].title)
    }

    inner class FiltersViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {

        fun bindTo(drinkX: String) {
            itemView.apply {
                filter_name.text = drinkX
                checkbox.isChecked = checkedFilters.contains(Filter(drinkX))

                this.setOnClickListener {
                    checkbox.isChecked = !checkbox.isChecked
                    if (checkbox.isChecked) {
                        checkedFilters.add(Filter(it.filter_name.text.toString()))

                    } else {
                        checkedFilters.remove(Filter(it.filter_name.text.toString()))
                    }

                }
            }
        }

    }
}