package com.teknasyon.satellitetracker.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.teknasyon.satellitetracker.R
import com.teknasyon.satellitetracker.data.model.Satellite
import com.teknasyon.satellitetracker.data.source.DataState
import com.teknasyon.satellitetracker.databinding.FragmentMainBinding
import com.teknasyon.satellitetracker.ui.base_classes.BaseFragment
import com.teknasyon.satellitetracker.util.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var adapter: SatellitesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
        subscribeToObservers()
        viewModel.readSatellites()
    }

    private fun subscribeToObservers() {
        viewModel.satellite.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    binding.loadingProgressBar.isGone = false
                }
                is DataState.Success -> {
                    binding.loadingProgressBar.isGone = true
                    adapter = SatellitesAdapter(
                        allItems = it.data,
                        callback = ::navigateToDetails
                    )
                    binding.recyclerView.adapter = adapter
                }
                is DataState.Error -> {
                    binding.loadingProgressBar.isGone = true
                    val message = "An error occurred: ${it.exception}"
                    Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.searchQuery.observe(viewLifecycleOwner) {
            adapter.filter.filter(it)
        }

    }

    private fun setUI() {
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        AppCompatResources.getDrawable(requireContext(), R.drawable.adapter_item_decoration)?.let {
            itemDecoration.setDrawable(it)
        }

        binding.recyclerView.apply {
            addItemDecoration(itemDecoration)
            setHasFixedSize(true)
        }

        binding.searchView.setOnQueryTextListener(
            object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.writeToSearchQuery(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
//                    adapter.filter.filter(newText)
                    viewModel.writeToSearchQuery(newText)
                    return false
                }
            }
        )
    }

    private fun navigateToDetails(satellite: Satellite) {
        val action = MainFragmentDirections.navigateToDetailsFragment(satellite = satellite)
        findNavController().navigate(action)
    }

}