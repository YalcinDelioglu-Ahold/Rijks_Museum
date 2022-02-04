package com.myd.rijksmuseum.presentation.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class InjectingFragmentFactory @Inject internal constructor() : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when(loadFragmentClass(classLoader, className)) {
            CollectionsFragment::class -> CollectionsFragment()
            DetailsFragment::class -> DetailsFragment()
            else -> super.instantiate(classLoader, className)
        }
}