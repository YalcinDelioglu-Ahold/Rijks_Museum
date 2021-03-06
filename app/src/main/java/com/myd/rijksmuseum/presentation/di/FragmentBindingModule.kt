package com.myd.rijksmuseum.presentation.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.recyclerview.widget.ListAdapter
import com.myd.rijksmuseum.domain.Collection
import com.myd.rijksmuseum.presentation.adapters.CollectionsAdapter
import com.myd.rijksmuseum.presentation.fragments.CollectionsFragment
import com.myd.rijksmuseum.presentation.fragments.DetailsFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class FragmentBindingModule {

    @FragmentScope
    @Binds
    @IntoMap
    @FragmentKey(CollectionsFragment::class)
    abstract fun bindCollectionsFragment(collectionsFragment: CollectionsFragment): Fragment

    @FragmentScope
    @Binds
    @IntoMap
    @FragmentKey(DetailsFragment::class)
    abstract fun bindDetailsFragment(detailsFragment: DetailsFragment): Fragment

    @FragmentScope
    @Binds
    abstract fun bindFragmentFactory(factory: FragmentFactory): FragmentFactory

    @FragmentScope
    @Binds
    abstract fun bindCollectionsAdapter(adapter: CollectionsAdapter):
            ListAdapter<Collection, CollectionsAdapter.CollectionItemViewHolder>

}