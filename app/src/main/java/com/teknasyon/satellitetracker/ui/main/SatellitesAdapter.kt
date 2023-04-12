package com.teknasyon.satellitetracker.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teknasyon.satellitetracker.databinding.AdapterItemSatellitesBinding
import com.teknasyon.satellitetracker.model.Satellite

class SatellitesAdapter(
    private val satellites: List<Satellite>,
    private val callback: (Satellite) -> Unit
) : RecyclerView.Adapter<SatellitesAdapter.SatellitesAdapterViewHolder>() {

    inner class SatellitesAdapterViewHolder(val binding: AdapterItemSatellitesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatellitesAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterItemSatellitesBinding.inflate(layoutInflater, parent, false)
        return SatellitesAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int = satellites.size

    override fun onBindViewHolder(holder: SatellitesAdapterViewHolder, position: Int) {
        val satellite = satellites[position]
        holder.binding.satellite = satellite
        holder.binding.root.setOnClickListener {
            callback(satellite)
        }
    }

}