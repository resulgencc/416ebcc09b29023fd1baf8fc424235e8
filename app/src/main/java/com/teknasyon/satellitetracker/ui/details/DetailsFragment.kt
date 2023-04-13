package com.teknasyon.satellitetracker.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.teknasyon.satellitetracker.R
import com.teknasyon.satellitetracker.data.model.Satellite
import com.teknasyon.satellitetracker.data.model.SatelliteDetails
import com.teknasyon.satellitetracker.data.source.DataState
import com.teknasyon.satellitetracker.databinding.FragmentDetailsBinding
import com.teknasyon.satellitetracker.ui.base_classes.BaseFragment
import com.teknasyon.satellitetracker.util.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment(R.layout.fragment_details){

    private val binding by viewBinding(FragmentDetailsBinding::bind)

    private val viewModel by viewModels<DetailsViewModel>()

    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
        viewModel.getDetails(satelliteId = args.satellite.id)
    }

    private fun subscribeToObservers() {
        viewModel.satelliteDetails.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {}
                is DataState.Success -> {
                    setUI(satellite = args.satellite, satelliteDetails = it.data)
                }
                is DataState.Error -> {
                    val message = "An error occurred: ${it.exception}"
                    Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setUI(satellite: Satellite, satelliteDetails: SatelliteDetails){
        binding.satellite = satellite
        binding.satelliteDetails = satelliteDetails
    }
}