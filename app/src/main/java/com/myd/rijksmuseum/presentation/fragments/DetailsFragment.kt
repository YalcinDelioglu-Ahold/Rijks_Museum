package com.myd.rijksmuseum.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.myd.rijksmuseum.R
import com.myd.rijksmuseum.databinding.DetailsFragmentBinding
import com.myd.rijksmuseum.presentation.viewmodels.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment @Inject internal constructor(): Fragment() {
    private lateinit var binding: DetailsFragmentBinding

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.details_fragment,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.detailsViewModel = viewModel

        val objectNumber = DetailsFragmentArgs.fromBundle(requireArguments()).objectNumber
        viewModel.objectNumberLiveData.postValue(objectNumber)

        return binding.root
    }
}