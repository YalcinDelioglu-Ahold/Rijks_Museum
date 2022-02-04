package com.myd.rijksmuseum.presentation.fragments

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainNavHostFragment : NavHostFragment() {

    @Inject
    lateinit var mainFragmentFactory: InjectingFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = mainFragmentFactory
    }
}