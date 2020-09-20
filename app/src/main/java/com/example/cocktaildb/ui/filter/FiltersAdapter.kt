package com.example.cocktaildb.ui.filter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktaildb.R
import kotlinx.android.synthetic.main.drink_item.view.*
import kotlinx.android.synthetic.main.filter_item.view.*

class FiltersAdapter : RecyclerView.Adapter<FiltersAdapter.FiltersViewHolder>() {
    private val checkedFilters = ArrayList<String>()
    private val filters = ArrayList<String>()
    fun setData(filters: List<String>, checkedFilters: List<String>) {
        this.filters.clear()
        this.checkedFilters.clear()

        this.filters.addAll(filters)
        this.checkedFilters.addAll(checkedFilters)
        notifyDataSetChanged()
    }

    fun getArrayOfFilters(): ArrayList<String> {
        val list = ArrayList<String>()
        filters.forEach {filter -> if (checkedFilters.contains(filter)) list.add(filter) }
        return list
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


        private fun <K, V> Map<out K, V>.getOrDefaultCompat(key: K, defaultValue: V):
                V {
            if (this.containsKey(key)) {
                return this[key] ?: defaultValue
            }
            return defaultValue
        }


    }
}