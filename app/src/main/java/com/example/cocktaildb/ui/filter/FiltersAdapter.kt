package com.example.cocktaildb.ui.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktaildb.R
import com.example.cocktaildb.data.entity.Filter
import kotlinx.android.synthetic.main.item_filter.view.*

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
        return FiltersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_filter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FiltersViewHolder, position: Int) {
        holder.bindTo(filters[position])
    }

    inner class FiltersViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
        fun bindTo(filter: Filter) {
            itemView.apply {
                bindTitle(filter)
                bindCheckBox(filter)

                setOnClickListener {
                    checkbox.isChecked = !checkbox.isChecked
                }
            }
        }

        private fun View.bindTitle(filter: Filter) {
            tvFilterTitle.text = filter.title
        }

        private fun View.bindCheckBox(filter: Filter) {
            checkbox.isChecked = checkedFilters.contains(filter)
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedFilters.add(Filter(tvFilterTitle.text.toString()))
                } else {
                    checkedFilters.remove(Filter(tvFilterTitle.text.toString()))
                }
            }
        }
    }
}