package com.myd.rijksmuseum.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.myd.rijksmuseum.R
import com.myd.rijksmuseum.databinding.CollectionsFragmentBinding
import com.myd.rijksmuseum.presentation.adapters.CollectionsAdapter
import com.myd.rijksmuseum.presentation.di.FragmentScope
import com.myd.rijksmuseum.presentation.viewmodels.CollectionsViewModel
import com.myd.rijksmuseum.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

@FragmentScope
class CollectionsFragment @Inject internal constructor(
    private val viewModelFactory: ViewModelFactory
) : Fragment() {
    private lateinit var binding: CollectionsFragmentBinding

    private val viewModel: CollectionsViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var adapter: CollectionsAdapter

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
        binding.collectionsViewModel = viewModel

        val layoutManager = LinearLayoutManager(context)
        binding.list.layoutManager = layoutManager
        binding.list.adapter = adapter

        adapter.onItemClickListener = {
            val action =
                CollectionsFragmentDirections.actionCollectionsFragmentToDetailsFragment()
            action.objectNumber = it.objectNumber
            findNavController().navigate(action)
        }

        viewModel.collectionsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }
}