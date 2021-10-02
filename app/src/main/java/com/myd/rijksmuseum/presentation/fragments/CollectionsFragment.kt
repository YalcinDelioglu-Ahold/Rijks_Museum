package com.myd.rijksmuseum.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.myd.rijksmuseum.R
import com.myd.rijksmuseum.databinding.CollectionsFragmentBinding
import com.myd.rijksmuseum.presentation.viewmodels.collections.CollectionsViewModel
import com.myd.rijksmuseum.presentation.viewmodels.collections.CollectionsViewModelFactory
import com.myd.rijksmuseum.usecases.GetCollectionsUseCase
import javax.inject.Inject

class CollectionsFragment @Inject internal constructor(
    collectionsUseCase: GetCollectionsUseCase
) : Fragment() {
    private lateinit var binding: CollectionsFragmentBinding
    private val viewModel: CollectionsViewModel by viewModels {
        CollectionsViewModelFactory(collectionsUseCase)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.collections_fragment,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}