package com.teknasyon.satellitetracker.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teknasyon.satellitetracker.data.model.Satellite
import com.teknasyon.satellitetracker.databinding.AdapterItemSatellitesBinding
import com.teknasyon.satellitetracker.util.filter.FilterableAdapter

class SatellitesAdapter(
    private val callback: (Satellite) -> Unit,
    allItems: List<Satellite>,
) : FilterableAdapter<Satellite, SatellitesAdapter.SatellitesAdapterViewHolder>(allItems = allItems) {

    inner class SatellitesAdapterViewHolder(val binding: AdapterItemSatellitesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatellitesAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterItemSatellitesBinding.inflate(layoutInflater, parent, false)
        return SatellitesAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SatellitesAdapterViewHolder, position: Int) {
        val satellite = getItems(position)
        holder.binding.satellite = satellite
        holder.binding.root.setOnClickListener {
            callback(satellite)
        }
    }

}