package com.teknasyon.satellitetracker.util.filter

import android.annotation.SuppressLint
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.teknasyon.satellitetracker.util.extensions.changeAll

abstract class FilterableAdapter<T : FilterItem, VH : RecyclerView.ViewHolder>(
    private val allItems: List<T>
) : RecyclerView.Adapter<VH>(), Filterable {

    private val filteredItems = mutableListOf<T>()
    private val cacheList = mutableListOf<CachedFilterItem<T>>()

    init {
        filteredItems.addAll(allItems)

        // add empty filter state to cache
        val emptyFilter = CachedFilterItem(
            key = "",
            items = allItems
        )
        cacheList.add(emptyFilter)
    }

    // adapter functions

    protected fun getItems(index: Int): T = filteredItems[index]

    final override fun getItemCount(): Int = filteredItems.size


    // filter functions

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val key = constraint?.toString()?.lowercase() ?: ""
            val isCached = searchInCache(key = key)

            if (isCached != null) {
                val filterResult = FilterResults()
                filterResult.values = isCached.items
                return filterResult

            } else {
                val newList = mutableListOf<T>()
                for (item in allItems) {
                    if (item.getFilterKey().lowercase().contains(key)) {
                        newList.add(item)
                    }
                }

                val cacheItem = CachedFilterItem(
                    key = key,
                    items = newList
                )

                cacheList.add(cacheItem)

                val filterResult = FilterResults()
                filterResult.values = newList
                return filterResult
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            val newList = results?.values as Collection<T>
            filteredItems.changeAll(newList)
            notifyDataSetChanged()
        }
    }

    private fun searchInCache(key: String): CachedFilterItem<T>? =
        cacheList.find { it.key.contains(key) }
}